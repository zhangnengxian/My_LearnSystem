package utils;


import com.alibaba.fastjson.JSON;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;
import pojo.ResultIdCard;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class IdCardUtil {
	   /**OCR webapi 接口地址*/
		private static final String WEBOCR_URL = "https://webapi.xfyun.cn/v1/service/v1/ocr/idcard";
		/**应用APPID（必须为webapi类型应用，并开通身份证识别服务，参考帖子如何创建一个webapi应用：http://bbs.xfyun.cn/forum.php?mod=viewthread&tid=36481）*/
		private static final String APPID = "5dfc986d";
		/**接口密钥（webapi类型应用开通身份证识别服务后，控制台--我的应用---身份证识别---相应服务的apikey）*/
		private static final String API_KEY = "258a9236cc0e58828b3a87595cabd914";


		// 引擎类型
		private static final String ENGINE_TYPE = "idcard";
		// 是否返回头像图片		
		private static final String HEAD_PORTRAIT = "1";
		// 是否返回身份证号码区域截图
		private static final String ID_NUMBER_IMAGE = "1";


		/**
		 * OCR WebAPI 调用示例程序
		 * @param base64ImgStr base64编码图片
		 * @return ResultIdCard
		 */
		public static ResultIdCard getResultInfo(String base64ImgStr){
			try {
			String imageBase64 = new String(base64ImgStr.getBytes(), "UTF-8");

			//错误码链接：https://www.xfyun.cn/document/error-code (code返回错误码时必看)
			String result =IdCardUtil.doPost(WEBOCR_URL, buildHttpHeader(), "image=" + URLEncoder.encode(imageBase64, "UTF-8"));
			if (StringUtils.isNotBlank(result)) {
				return JSON.parseObject(result, ResultIdCard.class);
			}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

			return null;
		}




		/**
		 * 组装http请求头
		 */
		private static Map<String, String> buildHttpHeader(){
			String curTime = System.currentTimeMillis() / 1000L + "";
			String param = "{\"engine_type\":\"" + ENGINE_TYPE + "\",\"head_portrait\":\"" + HEAD_PORTRAIT + "\",\"id_number_image\":\"" + ID_NUMBER_IMAGE +"\"}";
			String paramBase64 = null;
			try {
				paramBase64 = new String(Base64.encodeBase64(param.getBytes("UTF-8")));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			String checkSum = DigestUtils.md5DigestAsHex((API_KEY + curTime + paramBase64).getBytes());
			Map<String, String> header = new HashMap<String, String>();
			header.put("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
			header.put("X-Param", paramBase64);
			header.put("X-CurTime", curTime);
			header.put("X-CheckSum", checkSum);
			header.put("X-Appid", APPID);
			return header;
		}





		/**
		 * 发送post请求
		 * @param url
		 * @param header
		 * @param body
		 * @return
		 */
		private static String doPost(String url, Map<String, String> header, String body) {
			String result = "";
			BufferedReader in = null;
			PrintWriter out = null;
			try {
				// 设置 url
				URL realUrl = new URL(url);
				URLConnection connection = realUrl.openConnection();
				HttpURLConnection httpURLConnection = (HttpURLConnection) connection;
				// 设置 header
				for (String key : header.keySet()) {
					httpURLConnection.setRequestProperty(key, header.get(key));
				}
				// 设置请求 body
				httpURLConnection.setDoOutput(true);
				httpURLConnection.setDoInput(true);
				out = new PrintWriter(httpURLConnection.getOutputStream());
				// 保存body
				out.print(body);
				// 发送body
				out.flush();
				if (HttpURLConnection.HTTP_OK != httpURLConnection.getResponseCode()) {
					System.out.println("Http 请求失败，状态码：" + httpURLConnection.getResponseCode());
					return null;
				}

				// 获取响应body
				in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
				String line;
				while ((line = in.readLine()) != null) {
					result += line;
				}
			} catch (Exception e) {
				return null;
			}
			return result;
		}
}
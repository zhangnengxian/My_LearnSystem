package pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Data
@ApiModel(value = "IdCardVo对象",description = "身份证对象IdCardVo")
public class IdCardVo {

    @ApiModelProperty(value ="姓名" )
    private String name;

    @ApiModelProperty(value ="性别" )
    private String sex;

    @ApiModelProperty(value ="名族" )
    private String people;

    @ApiModelProperty(value ="出生日期" )
    private String birthday;

    @ApiModelProperty(value ="格式化处理后的出生日期" )
    private Date birthDate;

    @ApiModelProperty(value ="家庭住址" )
    private String address;

    @ApiModelProperty(value ="身份证号码" )
    private String id_number;

    @ApiModelProperty(value ="签发机关" )
    private String issue_authority;

    @ApiModelProperty(value ="正面还是背面" )
    private String type;

    @ApiModelProperty(value ="有效期限" )
    private String validity;

    @ApiModelProperty(value ="有效期开始时间" )
    private Date startDate;

    @ApiModelProperty(value ="有效期结束时间" )
    private Date endDate;



    public Date getBirthDate() {
        if (StringUtils.isNotBlank(this.birthday)) {
            return formtBirthday(this.birthday);
        }
        return null;
    }


    public Date getStartDate() {
        if (StringUtils.isNotBlank(this.validity)) {
            String[] split = validity.split("-");
            String StartDate = split[0].replace(".","-");
            return str2DateFormt(StartDate);
        }
        return null;
    }

    public Date getEndDate() {
        if (StringUtils.isNotBlank(this.validity)) {
            String[] split = validity.split("-");
            String StartDate = split[1].replace(".","-");
            return str2DateFormt(StartDate);
        }
        return null;
    }



    private Date formtBirthday(String birthdayStr) {

        birthdayStr = birthdayStr.replaceAll("年|月", "-").replace("日","");
        String[] split = birthdayStr.split("-");
        StringBuffer birthday=new StringBuffer();
        for (int i = 0; i <split.length ; i++) {
            String s = split[i];
            s=s.toUpperCase();
            s = s.replace("O", "0");
            s = String.format("%02d", Integer.valueOf(s));
            if (i!=split.length-1) {
                birthday.append(s).append("-");
            }else {
                birthday.append(s);
            }
        }
        return str2DateFormt(birthday.toString());
    }

    private Date str2DateFormt(String dateStr){
        //将读取的年月日拼接转日期
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

}

package utils;


public class OpencvUtils {
//
//    static {
//        //动态加载视觉库
//        String osName = FileTransplantUtil.systemOs();
//        String opencvpath = System.getProperty("user.dir") + "/opencv/lib/win/x64/";
//        //linux平台F:\projects\贵州支付\lecent-weixinpay-exchange\opencv\lib\win\x64\opencv_java342.dll
//        if (FileTransplantUtil.LINUX_OS.equals(osName)) {
//            opencvpath = System.getProperty("user.dir") + "/opencv/lib/linux/";
//            System.load(opencvpath + Core.NATIVE_LIBRARY_NAME + ".os");
//        } else {
//            System.load(opencvpath + Core.NATIVE_LIBRARY_NAME + ".dll");
//        }
//    }
//
//
//    private static final int BLACK = 0;
//    private static final int WHITE = 255;
//
//
//    /**
//     * 剪切身份证区域
//     *
//     * @param mat
//     */
//    public static Mat houghLinesP(Mat begin, Mat mat) {
//        //灰度
//        mat = OpencvUtils.gray(mat);
//        //二值化
//        mat = OpencvUtils.binary(mat);
//        //腐蚀
//        mat = OpencvUtils.erode(mat, 5);
//        //边缘检测
//        mat = OpencvUtils.carry(mat);
//        //降噪
//        //mat = OpencvUtils.navieRemoveNoise(mat, 1);
//        //膨胀
//        mat = OpencvUtils.dilate(mat, 10);
//
//        //轮廓检测,清除小的轮廓部分
//        List<MatOfPoint> contours = OpencvUtils.findContours(mat);
//        for (int i = 0; i < contours.size(); i++) {
//            double area = OpencvUtils.area(contours.get(i));
//            if (area < 5000) {
//                Imgproc.drawContours(mat, contours, i, new Scalar(0, 0, 0), -1);
//            }
//        }
//        Mat storage = new Mat();
//        Imgproc.HoughLinesP(mat, storage, 1, Math.PI / 180, 10, 0, 10);
//        double[] maxLine = new double[]{0, 0, 0, 0};
//        //获取最长的直线
//        for (int x = 0; x < storage.rows(); x++) {
//            double[] vec = storage.get(x, 0);
//            double x1 = vec[0], y1 = vec[1], x2 = vec[2], y2 = vec[3];
//            double newLength = Math.sqrt(Math.abs((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)));
//            double oldLength = Math.sqrt(Math.abs((maxLine[0] - maxLine[2]) * (maxLine[0] - maxLine[2]) + (maxLine[1] - maxLine[3]) * (maxLine[1] - maxLine[3])));
//            if (newLength > oldLength) {
//                maxLine = vec;
//            }
//        }
//        //计算最长线的角度
//        double angle = getAngle(maxLine[0], maxLine[1], maxLine[2], maxLine[3]);
//        //旋转角度
//        mat = rotate3(mat, angle);
//        begin = rotate3(begin, angle);
//
//        Imgproc.HoughLinesP(mat, storage, 1, Math.PI / 180, 10, 10, 10);
//        List<double[]> lines = new ArrayList<>();
//
//        //在mat上划线
//        for (int x = 0; x < storage.rows(); x++) {
//            double[] vec = storage.get(x, 0);
//            double x1 = vec[0], y1 = vec[1], x2 = vec[2], y2 = vec[3];
//            Point start = new Point(x1, y1);
//            Point end = new Point(x2, y2);
//            //获取与图像x边缘近似平行的直线
//            if (Math.abs(start.y - end.y) < 5) {
//                if (Math.abs(x2 - x1) > 20) {
//                    lines.add(vec);
//                }
//            }
//            //获取与图像y边缘近似平行的直线
//            if (Math.abs(start.x - end.x) < 5) {
//                if (Math.abs(y2 - y1) > 20) {
//                    lines.add(vec);
//                }
//            }
//        }
//
//
//        //获取最大的和最小的X,Y坐标
//        double maxX = 0.0, minX = 10000, minY = 10000, maxY = 0.0;
//        for (int i = 0; i < lines.size(); i++) {
//            double[] vec = lines.get(i);
//            double x1 = vec[0], y1 = vec[1], x2 = vec[2], y2 = vec[3];
//            maxX = maxX > x1 ? maxX : x1;
//            maxX = maxX > x2 ? maxX : x2;
//            minX = minX > x1 ? x1 : minX;
//            minX = minX > x2 ? x2 : minX;
//            maxY = maxY > y1 ? maxY : y1;
//            maxY = maxY > y2 ? maxY : y2;
//            minY = minY > y1 ? y1 : minY;
//            minY = minY > y2 ? y2 : minY;
//        }
//
//
//        if (maxX < mat.cols() && minX > 0 && maxY < mat.rows() && minY > 0) {
//            List<Point> list = new ArrayList<>();
//            Point point1 = new Point(minX + 10, minY + 10);
//            Point point2 = new Point(minX + 10, maxY - 10);
//            Point point3 = new Point(maxX - 10, minY + 10);
//            Point point4 = new Point(maxX - 10, maxY - 10);
//            list.add(point1);
//            list.add(point2);
//            list.add(point3);
//            list.add(point4);
//            mat = shear(begin, list);
//        } else {
//            mat = begin;
//        }
//        return mat;
//    }/
//
//    /**
//     * 灰化处理
//     */
//    public static Mat gray(Mat mat) {
//        Mat gray = new Mat();
//        Imgproc.cvtColor(mat, gray, Imgproc.COLOR_BGR2GRAY, 1);
//        return gray;
//    }
//
//    /**
//     * 二值化处理
//     */
//    public static Mat binary(Mat mat) {
//        Mat binary = new Mat();
//        //Imgproc.adaptiveThreshold(mat, binary, 255, Imgproc.ADAPTIVE_THRESH_MEAN_C, Imgproc.THRESH_BINARY_INV, 25, 10);
//        Imgproc.adaptiveThreshold(mat, binary, 255, Imgproc.ADAPTIVE_THRESH_MEAN_C, Imgproc.THRESH_BINARY_INV, 45, 20);
//        //Imgproc.adaptiveThreshold(mat, binary, 255, Imgproc.ADAPTIVE_THRESH_MEAN_C, Imgproc.THRESH_BINARY_INV, 57, 25);
//        //Imgproc.adaptiveThreshold(mat, binary, 255, Imgproc.ADAPTIVE_THRESH_MEAN_C, Imgproc.THRESH_BINARY_INV, 73, 33);
//        //Imgproc.adaptiveThreshold(mat, binary, 255, Imgproc.ADAPTIVE_THRESH_MEAN_C, Imgproc.THRESH_BINARY_INV, 91, 40);
//        //Imgproc.adaptiveThreshold(mat, binary, 255, Imgproc.ADAPTIVE_THRESH_MEAN_C, Imgproc.THRESH_BINARY_INV, 103, 45);
//        return binary;
//    }
//
//
//    /**
//     * 腐蚀
//     */
//    public static Mat erode(Mat mat, int size) {
//        Mat erode = new Mat();
//        Mat element = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(size, size));
//        //腐蚀
//        Imgproc.erode(mat, erode, element, new Point(-1, -1), 1);
//        return erode;
//    }
//
//
//    /**
//     * 边缘检测
//     */
//    public static Mat carry(Mat mat) {
//        Mat dst = new Mat();
//        //高斯平滑滤波器卷积降噪
//        Imgproc.GaussianBlur(mat, dst, new Size(3, 3), 0);
//        //边缘检测
//        Imgproc.Canny(mat, dst, 50, 150);
//        return dst;
//    }
//
//
//    /**
//     * 轮廓检测
//     */
//    public static List<MatOfPoint> findContours(Mat mat) {
//        List<MatOfPoint> contours = new ArrayList<>();
//        Mat hierarchy = new Mat();
//        Imgproc.findContours(mat, contours, hierarchy, Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);
//        return contours;
//    }
//
//
//    /**
//     * 清除小面积轮廓
//     */
//    public static Mat drawContours(Mat mat, int size) {
//        List<MatOfPoint> cardContours = OpencvUtils.findContours(mat);
//        for (int i = 0; i < cardContours.size(); i++) {
//            double area = OpencvUtils.area(cardContours.get(i));
//            if (area < size) {
//                Imgproc.drawContours(mat, cardContours, i, new Scalar(0, 0, 0), -1);
//            }
//        }
//        return mat;
//    }
//
//
//    /**
//     * 8邻域降噪,又有点像9宫格降噪;即如果9宫格中心被异色包围，则同化
//     *
//     * @param pNum 默认值为1
//     */
//    public static Mat navieRemoveNoise(Mat mat, int pNum) {
//        int i, j, m, n, nValue, nCount;
//        int nWidth = mat.cols();
//        int nHeight = mat.rows();
//
//        // 如果一个点的周围都是白色的，而它确是黑色的，删除它
//        for (j = 1; j < nHeight - 1; ++j) {
//            for (i = 1; i < nWidth - 1; ++i) {
//                nValue = (int) mat.get(j, i)[0];
//                if (nValue == 0) {
//                    nCount = 0;
//                    // 比较以(j ,i)为中心的9宫格，如果周围都是白色的，同化
//                    for (m = j - 1; m <= j + 1; ++m) {
//                        for (n = i - 1; n <= i + 1; ++n) {
//                            if ((int) mat.get(m, n)[0] == 0) {
//                                nCount++;
//                            }
//                        }
//                    }
//                    if (nCount <= pNum) {
//                        // 周围黑色点的个数小于阀值pNum,把该点设置白色
//                        mat.put(j, i, WHITE);
//                    }
//                } else {
//                    nCount = 0;
//                    // 比较以(j ,i)为中心的9宫格，如果周围都是黑色的，同化
//                    for (m = j - 1; m <= j + 1; ++m) {
//                        for (n = i - 1; n <= i + 1; ++n) {
//                            if ((int) mat.get(m, n)[0] == 0) {
//                                nCount++;
//                            }
//                        }
//                    }
//                    if (nCount >= 7) {
//                        // 周围黑色点的个数大于等于7,把该点设置黑色;即周围都是黑色
//                        mat.put(j, i, BLACK);
//                    }
//                }
//            }
//        }
//        return mat;
//    }
//
//
//    /**
//     * 膨胀
//     */
//    public static Mat dilate(Mat mat, int size) {
//        Mat dilate = new Mat();
//        Mat element = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(size, size));
//        //膨胀
//        Imgproc.dilate(mat, dilate, element, new Point(-1, -1), 1);
//        return dilate;
//    }
//
//
//    /**
//     * 计算角度
//     */
//    public static double getAngle(double px1, double py1, double px2, double py2) {
//        //两点的x、y值
//        double x = px2 - px1;
//        double y = py2 - py1;
//        double hypotenuse = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
//        //斜边长度
//        double cos = x / hypotenuse;
//        double radian = Math.acos(cos);
//        //求出弧度
//        double angle = 180 / (Math.PI / radian);
//        //用弧度算出角度
//        if (y < 0) {
//            angle = -angle;
//        } else if ((y == 0) && (x < 0)) {
//            angle = 180;
//        }
//        while (angle < 0) {
//            angle = angle + 90;
//        }
//        return angle;
//    }
//
//
//    /**
//     * 图片旋转
//     */
//    public static Mat rotate3(Mat splitImage, double angle) {
//        double thera = angle * Math.PI / 180;
//        double a = Math.sin(thera);
//        double b = Math.cos(thera);
//
//        int wsrc = splitImage.width();
//        int hsrc = splitImage.height();
//
//        int wdst = (int) (hsrc * Math.abs(a) + wsrc * Math.abs(b));
//        int hdst = (int) (wsrc * Math.abs(a) + hsrc * Math.abs(b));
//        Mat imgDst = new Mat(hdst, wdst, splitImage.type());
//
//        Point pt = new Point(splitImage.cols() / 2, splitImage.rows() / 2);
//        // 获取仿射变换矩阵
//        Mat affineTrans = Imgproc.getRotationMatrix2D(pt, angle, 1.0);
//
//        //System.out.println(affineTrans.dump());
//        // 改变变换矩阵第三列的值
//        affineTrans.put(0, 2, affineTrans.get(0, 2)[0] + (wdst - wsrc) / 2);
//        affineTrans.put(1, 2, affineTrans.get(1, 2)[0] + (hdst - hsrc) / 2);
//
//        Imgproc.warpAffine(splitImage, imgDst, affineTrans, imgDst.size(),
//                Imgproc.INTER_CUBIC | Imgproc.WARP_FILL_OUTLIERS);
//        return imgDst;
//    }
//
//
//    /**
//     * 根据四点坐标截取模板图片
//     */
//    public static Mat shear(Mat mat, List<Point> pointList) {
//        int x = minX(pointList);
//        int y = minY(pointList);
//        int xl = xLength(pointList) > mat.cols() - x ? mat.cols() - x : xLength(pointList);
//        int yl = yLength(pointList) > mat.rows() - y ? mat.rows() - y : yLength(pointList);
//        Rect re = new Rect(x, y, xl, yl);
//        return new Mat(mat, re);
//    }
//
//    /**
//     * 获取轮廓的面积
//     */
//    public static double area(MatOfPoint contour) {
//        MatOfPoint2f mat2f = new MatOfPoint2f();
//        contour.convertTo(mat2f, CvType.CV_32FC1);
//        RotatedRect rect = Imgproc.minAreaRect(mat2f);
//        return rect.boundingRect().area();
//    }
//
//
//    /**
//     * 获取最小的X坐标
//     *
//     * @param points
//     * @return
//     */
//    public static int minX(List<Point> points) {
//        Collections.sort(points, new XComparator(false));
//        return (int) (points.get(0).x > 0 ? points.get(0).x : -points.get(0).x);
//    }
//
//    /**
//     * 获取最小的Y坐标
//     *
//     * @param points
//     * @return
//     */
//    public static int minY(List<Point> points) {
//        Collections.sort(points, new YComparator(false));
//        return (int) (points.get(0).y > 0 ? points.get(0).y : -points.get(0).y);
//    }
//
//    /**
//     * 获取最长的X坐标距离
//     *
//     * @param points
//     * @return
//     */
//    public static int xLength(List<Point> points) {
//        Collections.sort(points, new XComparator(false));
//        return (int) (points.get(3).x - points.get(0).x);
//    }
//
//    /**
//     * 获取最长的Y坐标距离
//     *
//     * @param points
//     * @return
//     */
//    public static int yLength(List<Point> points) {
//        Collections.sort(points, new YComparator(false));
//        return (int) (points.get(3).y - points.get(0).y);
//    }
//
//    //集合排序规则（根据X坐标排序）
//    public static class XComparator implements Comparator<Point> {
//        private boolean reverseOrder; // 是否倒序
//
//        public XComparator(boolean reverseOrder) {
//            this.reverseOrder = reverseOrder;
//        }
//
//        @Override
//        public int compare(Point arg0, Point arg1) {
//            if (reverseOrder) {
//                return (int) arg1.x - (int) arg0.x;
//            } else {
//                return (int) arg0.x - (int) arg1.x;
//            }
//        }
//    }
//
//    //集合排序规则（根据Y坐标排序）
//    public static class YComparator implements Comparator<Point> {
//        private boolean reverseOrder; // 是否倒序
//
//        public YComparator(boolean reverseOrder) {
//            this.reverseOrder = reverseOrder;
//        }
//
//        @Override
//        public int compare(Point arg0, Point arg1) {
//            if (reverseOrder) {
//                return (int) arg1.y - (int) arg0.y;
//            } else {
//                return (int) arg0.y - (int) arg1.y;
//            }
//        }
//    }
//
//    /**
//     * Mat转换成BufferedImage
//     *
//     * @param matrix        要转换的Mat
//     * @param fileExtension 格式为 ".jpg", ".png", etc
//     */
//    public static BufferedImage Mat2BufImg(Mat matrix, String fileExtension) {
//        MatOfByte mob = new MatOfByte();
//        Imgcodecs.imencode(fileExtension, matrix, mob);
//        byte[] byteArray = mob.toArray();
//        BufferedImage bufImage = null;
//        try {
//            InputStream in = new ByteArrayInputStream(byteArray);
//            bufImage = ImageIO.read(in);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return bufImage;
//    }
//
//
//    /**
//     * 将base64转为Mat
//     **/
//    public static Mat base642Mat(String base64) {
//        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
//        try {
//            // 对base64进行解码
//            BASE64Decoder decoder = new BASE64Decoder();
//            byte[] origin = decoder.decodeBuffer(base64);
//            InputStream in = new ByteArrayInputStream(origin); // 将b作为输入流；
//            BufferedImage image = ImageIO.read(in);
//            Mat matImage = OpencvUtils.BufImg2Mat(image, BufferedImage.TYPE_3BYTE_BGR, CvType.CV_8UC3);// CvType.CV_8UC3
//            return matImage;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//
//    /**
//     * 将流转Mat
//     **/
//    public static Mat BufImg2Mat(BufferedImage original, int imgType, int matType) {
//        if (original == null) {
//            throw new IllegalArgumentException("original == null");
//        }
//        if (original.getType() != imgType) {
//            BufferedImage image = new BufferedImage(original.getWidth(), original.getHeight(), imgType);
//            Graphics2D g = image.createGraphics();
//            try {
//                g.setComposite(AlphaComposite.Src);
//                g.drawImage(original, 0, 0, null);
//            } finally {
//                g.dispose();
//            }
//        }
//        byte[] pixels = ((DataBufferByte) original.getRaster().getDataBuffer()).getData();
//        Mat mat = Mat.eye(original.getHeight(), original.getWidth(), matType);
//        mat.put(0, 0, pixels);
//        return mat;
//    }
//
//    /**
//     * 图片转Base64
//     **/
//    public static String ImageToBase64(String imgFile) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
//        InputStream in = null;
//        byte[] data = null;
//        // 读取图片字节数组
//        try {
//            in = new FileInputStream(imgFile);
//            data = new byte[in.available()];
//            in.read(data);
//            in.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        // 对字节数组Base64编码
//        BASE64Encoder encoder = new BASE64Encoder();
//        return encoder.encode(data);// 返回Base64编码过的字节数组字符串
//    }
//
//
//    /**
//     * 验证有效期是否有效
//     **/
//    public static boolean isValid(String validityPeriod) {
//        if (StringUtils.isNotBlank(validityPeriod)) {
//            String[] split = validityPeriod.split("-");
//            List<Date> dateList = new ArrayList<>();
//            for (int i = 0; i < split.length; i++) {
//                String s = split[i].trim();
//                String year = s.substring(0, 4);
//                String month = s.substring(4, 6);
//                String day = s.substring(6, 8);
//                String date = year + "-" + month + "-" + day;
//                System.out.println(date);
//                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//                try {
//                    dateList.add(format.parse(date));
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            Date startDate = dateList.get(0);//起始日期
//            Date endDate = dateList.get(1); //结束日期
//            Date currentDate = new Date();    //当前日期
//
//            boolean isBefore = startDate.before(currentDate); //startDate在currentDate之前返回true
//            boolean isAfter = endDate.after(currentDate);     //endDate在currentDate之后返回true
//            if (isBefore && isAfter) {
//                return true;
//            } else {
//                return false;
//            }
//        }
//        return false;
//    }


}

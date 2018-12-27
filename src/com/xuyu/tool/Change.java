package com.xuyu.tool;


/**
 * 转换类，有赞库存转换成teacher_time.jsp里面的对应value值，以及由老师设置界面的的value值转换成有赞商城对应商品的ItemNo
 */
public class Change {
    /**
     * 存储有赞商品库存的值
     */
    int stockValue[] = new int[21];

    /**
     * 存储有赞商品库存的转换标志的值  BUSY 或 IDEL
     */
    int flagValue[] = new int[21];

    /**
     * 初始化函数，初始化后可根据该函数初始化的结果获得库存和标志信息
     *
     * @param value 默认可选
     * @param cnt   有课不可选
     */
    public void initQuantityAndFlag(String value[], String cnt) {
        for (int i = 1; i < 21; i++) {
            stockValue[i] = 0;
            flagValue[i] = 0;
        }
        if (!(value == null || (value != null && value.length == 0))) {
            for (int i = 0; i < value.length; i++) {
                stockValue[Integer.parseInt(value[i])] = 1;
                flagValue[Integer.parseInt(value[i])] = 1;
            }
        }
        if(!cnt.equals("")&&cnt.length()!=0){
            String Cnt[] = cnt.split(",");
            if (!(Cnt == null || (Cnt != null && Cnt.length == 0))) {
                for (int i = 0; i < Cnt.length; i++) {
                    stockValue[Integer.parseInt(Cnt[i])] = 0;
                    flagValue[Integer.parseInt(Cnt[i])] = 2;
                }
            }
        }
    }

    /**
     * 设置对应的值，从有赞库存获取有课的时间，变成Teacher_time要用到的数据类型
     *
     * @param time
     * @return
     */
    public static int setValue(String time) {
        int i = -1;
        switch (time) {
            case "周一上午":
                i = 0;
                break;
            case "周一下午":
                i = 1;
                break;
            case "周一晚上":
                i = 2;
                break;
            case "周二上午":
                i = 3;
                break;
            case "周二下午":
                i = 4;
                break;
            case "周二晚上":
                i = 5;
                break;
            case "周三上午":
                i = 6;
                break;
            case "周三下午":
                i = 7;
                break;
            case "周三晚上":
                i = 8;
                break;
            case "周四上午":
                i = 9;
                break;
            case "周四下午":
                i = 10;
                break;
            case "周四晚上":
                i = 11;
                break;
            case "周五上午":
                i = 12;
                break;
            case "周五下午":
                i = 13;
                break;
            case "周五晚上":
                i = 14;
                break;
            case "周六上午":
                i = 15;
                break;
            case "周六下午":
                i = 16;
                break;
            case "周六晚上":
                i = 17;
                break;
            case "周日上午":
                i = 18;
                break;
            case "周日下午":
                i = 19;
                break;
            case "周日晚上":
                i = 20;
                break;
            default:
                break;
        }
        return i;
    }

    /**
     * 根据用户在设置时间界面勾选的值，获取相对应的库存值
     *
     * @param time
     * @return
     */
    public int getQuantity(String time) {
        int i = 0;
        switch (time) {
            case "周一上午":
                i = stockValue[0];
                break;
            case "周一下午":
                i = stockValue[1];
                break;
            case "周一晚上":
                i = stockValue[2];
                break;
            case "周二上午":
                i = stockValue[3];
                break;
            case "周二下午":
                i = stockValue[4];
                break;
            case "周二晚上":
                i = stockValue[5];
                break;
            case "周三上午":
                i = stockValue[6];
                break;
            case "周三下午":
                i = stockValue[7];
                break;
            case "周三晚上":
                i = stockValue[8];
                break;
            case "周四上午":
                i = stockValue[9];
                break;
            case "周四下午":
                i = stockValue[10];
                break;
            case "周四晚上":
                i = stockValue[11];
                break;
            case "周五上午":
                i = stockValue[12];
                break;
            case "周五下午":
                i = stockValue[13];
                break;
            case "周五晚上":
                i = stockValue[14];
                break;
            case "周六上午":
                i = stockValue[15];
                break;
            case "周六下午":
                i = stockValue[16];
                break;
            case "周六晚上":
                i = stockValue[17];
                break;
            case "周日上午":
                i = stockValue[18];
                break;
            case "周日下午":
                i = stockValue[19];
                break;
            case "周日晚上":
                i = stockValue[20];
                break;
            default:
                break;
        }
        return i;
    }

    /**
     * 根据由赞读入的库存
     *
     * @param time
     * @return
     */
    public int getValueb(String time) {
        int i = 0;
        switch (time) {
            case "周一上午":
                i = flagValue[0];
                break;
            case "周一下午":
                i = flagValue[1];
                break;
            case "周一晚上":
                i = flagValue[2];
                break;
            case "周二上午":
                i = flagValue[3];
                break;
            case "周二下午":
                i = flagValue[4];
                break;
            case "周二晚上":
                i = flagValue[5];
                break;
            case "周三上午":
                i = flagValue[6];
                break;
            case "周三下午":
                i = flagValue[7];
                break;
            case "周三晚上":
                i = flagValue[8];
                break;
            case "周四上午":
                i = flagValue[9];
                break;
            case "周四下午":
                i = flagValue[10];
                break;
            case "周四晚上":
                i = flagValue[11];
                break;
            case "周五上午":
                i = flagValue[12];
                break;
            case "周五下午":
                i = flagValue[13];
                break;
            case "周五晚上":
                i = flagValue[14];
                break;
            case "周六上午":
                i = flagValue[15];
                break;
            case "周六下午":
                i = flagValue[16];
                break;
            case "周六晚上":
                i = flagValue[17];
                break;
            case "周日上午":
                i = flagValue[18];
                break;
            case "周日下午":
                i = flagValue[19];
                break;
            case "周日晚上":
                i = flagValue[20];
                break;
            default:
                break;
        }
        return i;
    }

    /**
     * 根据对应的值获取该商品前面的标志 BUSY 或 IDLE
     *
     * @param i
     * @return
     */
    public String getSign(int i) {
        String a = null;
        switch (i) {
            case 0:
                a = "BUSY";
                break;
            case 1:
                a = "IDLE";
                break;
            case 2:
                a = "IDLE";
                break;
            default:
                break;
        }
        return a;
    }

    /**
     * 获得商品对应的ItemNo
     *
     * @param m
     * @return
     */
    public String getItemNo(int m) {
        String time = null;   //保存时间字符串
        String time1 = null;   //返回时间字符串
        switch (m) {
            case 0:
                time = "周一上午";
                break;
            case 1:
                time = "周一下午";
                break;
            case 2:
                time = "周一晚上";
                break;
            case 3:
                time = "周二上午";
                break;
            case 4:
                time = "周二下午";
                break;
            case 5:
                time = "周二晚上";
                break;
            case 6:
                time = "周三上午";
                break;
            case 7:
                time = "周三下午";
                break;
            case 8:
                time = "周三晚上";
                break;
            case 9:
                time = "周四上午";
                break;
            case 10:
                time = "周四下午";
                break;
            case 11:
                time = "周四晚上";
                break;
            case 12:
                time = "周五上午";
                break;
            case 13:
                time = "周五下午";
                break;
            case 14:
                time = "周五晚上";
                break;
            case 15:
                time = "周六上午";
                break;
            case 16:
                time = "周六下午";
                break;
            case 17:
                time = "周六晚上";
                break;
            case 18:
                time = "周日上午";
                break;
            case 19:
                time = "周日下午";
                break;
            case 20:
                time = "周日晚上";
                break;
            default:
                break;
        }
        if (!time.isEmpty()) {
            TimeUtils abouttime = new TimeUtils();
            time1 = "IDLE" + abouttime.change(time);
        }

        return time1;
    }

    /**
     * 获得一周内每天标志为IDLE的ItemNo
     *
     * @param b
     * @return
     */
    public String[] getIDLEItemNo(int b[]) {
        String[] weekIDLEItemNo = new String[7];
        StringBuffer Monday = new StringBuffer();
        StringBuffer Tuesday = new StringBuffer();
        StringBuffer Wednesday = new StringBuffer();
        StringBuffer Thursday = new StringBuffer();
        StringBuffer Friday = new StringBuffer();
        StringBuffer Saturday = new StringBuffer();
        StringBuffer Sunday = new StringBuffer();
        int i1 = 0, i2 = 0, i3 = 0, i4 = 0, i5 = 0, i6 = 0, i7 = 0;
        for (int i = 0; i < b.length; i++) {
            switch (b[i] / 3) {
                case 0:
                    if (i1 > 0 && i1 < 3) {
                        Monday.append(",");
                    }
                    Monday.append(getItemNo(b[i]));
                    i1++;
                    break;
                case 1:
                    if (i2 > 0 && i2 < 3) {
                        Tuesday.append(",");
                    }
                    Tuesday.append(getItemNo(b[i]));
                    i2++;
                    break;
                case 2:
                    if (i3 > 0 && i3 < 3) {
                        Wednesday.append(",");
                    }
                    Wednesday.append(getItemNo(b[i]));
                    i3++;
                    break;
                case 3:
                    if (i4 > 0 && i4 < 3) {
                        Thursday.append(",");
                    }
                    Thursday.append(getItemNo(b[i]));
                    i4++;
                    break;
                case 4:
                    if (i5 > 0 && i5 < 3) {
                        Friday.append(",");
                    }
                    Friday.append(getItemNo(b[i]));
                    i5++;
                    break;
                case 5:
                    if (i6 > 0 && i6 < 3) {
                        Saturday.append(",");
                    }
                    Saturday.append(getItemNo(b[i]));
                    i6++;
                    break;
                case 6:
                    if (i7 > 0 && i7 < 3) {
                        Sunday.append(",");
                    }
                    Sunday.append(getItemNo(b[i]));
                    i7++;
                    break;
                default:
                    break;
            }

        }
        weekIDLEItemNo[0] = Monday.toString();
        weekIDLEItemNo[1] = Tuesday.toString();
        weekIDLEItemNo[2] = Wednesday.toString();
        weekIDLEItemNo[3] = Thursday.toString();
        weekIDLEItemNo[4] = Friday.toString();
        weekIDLEItemNo[5] = Saturday.toString();
        weekIDLEItemNo[6] = Sunday.toString();
        return weekIDLEItemNo;
    }
}

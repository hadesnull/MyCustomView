package com.cai.yi.myapplication0.bean;

import java.util.List;

/**
 * Created by qf on 2017/9/14.
 */

public class DetailBean {

    /**
     * result : [{"_id":"10811201","title":"法国国王路易六世出生","pic":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/toh/201112/1/8715724471.jpg","year":1081,"month":12,"day":1,"des":"在936年前的今天，1081年12月1日 (农历十月廿八)，法国国王路易六世出生。","content":"在936年前的今天，1081年12月1日 (农历十月廿八)，法国国王路易六世出生。\n法国国王(1108∼1137年在位)。1098年由其父腓力一世指定为继承人，早在1108年腓力去世前，他已是实际统治者。他很快认识到首要的事情是使拥有王家领地的那些难以驾驭的贵族就范。他在位期间，大部分时间花费在与贵族进行斗争上。他实行绥靖政策，与教会和教士保持良好关系。\n路易六世致力于巩固法国的王权。1109年\u20141112年，他与英格兰国王亨利一世作战。在法国国内，他顺利地进行反对有独立倾向的诸侯的斗争。路易六世给城市居民以自治权，使他们能够在他与贵族的斗争中站在他的一边。依赖市民和教会的支持，路易逐一拆毁诸侯的城堡，并强制在他们的领地上驻扎忠于王室的卫队。至路易六世去世时。卡佩王朝在法国的统治已经比较稳定。\n从1130年开始，圣丹尼斯修道院院长叙热成为路易六世的主要顾问。叙热在扩大王权方面颇有成效，还为路易写了一部传记。路易六世的遗体也安葬在圣丹尼斯修道院。\n","lunar":"辛酉年十月廿八"}]
     * reason : 请求成功！
     * error_code : 0
     */

    private String reason;
    private int error_code;
    private List<ResultBean> result;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * _id : 10811201
         * title : 法国国王路易六世出生
         * pic : http://juheimg.oss-cn-hangzhou.aliyuncs.com/toh/201112/1/8715724471.jpg
         * year : 1081
         * month : 12
         * day : 1
         * des : 在936年前的今天，1081年12月1日 (农历十月廿八)，法国国王路易六世出生。
         * content : 在936年前的今天，1081年12月1日 (农历十月廿八)，法国国王路易六世出生。
         法国国王(1108∼1137年在位)。1098年由其父腓力一世指定为继承人，早在1108年腓力去世前，他已是实际统治者。他很快认识到首要的事情是使拥有王家领地的那些难以驾驭的贵族就范。他在位期间，大部分时间花费在与贵族进行斗争上。他实行绥靖政策，与教会和教士保持良好关系。
         路易六世致力于巩固法国的王权。1109年—1112年，他与英格兰国王亨利一世作战。在法国国内，他顺利地进行反对有独立倾向的诸侯的斗争。路易六世给城市居民以自治权，使他们能够在他与贵族的斗争中站在他的一边。依赖市民和教会的支持，路易逐一拆毁诸侯的城堡，并强制在他们的领地上驻扎忠于王室的卫队。至路易六世去世时。卡佩王朝在法国的统治已经比较稳定。
         从1130年开始，圣丹尼斯修道院院长叙热成为路易六世的主要顾问。叙热在扩大王权方面颇有成效，还为路易写了一部传记。路易六世的遗体也安葬在圣丹尼斯修道院。

         * lunar : 辛酉年十月廿八
         */

        private String _id;
        private String title;
        private String pic;
        private int year;
        private int month;
        private int day;
        private String des;
        private String content;
        private String lunar;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getLunar() {
            return lunar;
        }

        public void setLunar(String lunar) {
            this.lunar = lunar;
        }

        @Override
        public String toString() {
            return "ResultBean{" +
                    "_id='" + _id + '\'' +
                    ", title='" + title + '\'' +
                    ", pic='" + pic + '\'' +
                    ", year=" + year +
                    ", month=" + month +
                    ", day=" + day +
                    ", des='" + des + '\'' +
                    ", content='" + content + '\'' +
                    ", lunar='" + lunar + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "DetailBean{" +
                "reason='" + reason + '\'' +
                ", error_code=" + error_code +
                ", result=" + result +
                '}';
    }
}

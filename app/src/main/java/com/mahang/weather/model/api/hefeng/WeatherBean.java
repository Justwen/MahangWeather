package com.mahang.weather.model.api.hefeng;

import java.util.List;

/**
 * Created by Justwen on 2018/2/4.
 */

public class WeatherBean {


    private List<HeWeather6Bean> HeWeather6;

    public List<HeWeather6Bean> getHeWeather6() {
        return HeWeather6;
    }

    public void setHeWeather6(List<HeWeather6Bean> HeWeather6) {
        this.HeWeather6 = HeWeather6;
    }

    public static class HeWeather6Bean {
        /**
         * basic : {"cid":"CN101030100","location":"天津","parent_city":"天津","admin_area":"天津","cnty":"中国","lat":"39.12559509","lon":"117.19018555","tz":"+8.0"}
         * update : {"loc":"2018-02-04 12:51","utc":"2018-02-04 04:51"}
         * status : ok
         * now : {"cloud":"24","cond_code":"100","cond_txt":"晴","fl":"-7","hum":"18","pcpn":"0.0","pres":"1036","tmp":"1","vis":"10","wind_deg":"225","wind_dir":"西南风","wind_sc":"微风","wind_spd":"9"}
         * daily_forecast : [{"cond_code_d":"100","cond_code_n":"100","cond_txt_d":"晴","cond_txt_n":"晴","date":"2018-02-04","hum":"21","mr":"21:52","ms":"09:37","pcpn":"0.0","pop":"0","pres":"1034","sr":"07:15","ss":"17:35","tmp_max":"2","tmp_min":"-6","uv_index":"2","vis":"20","wind_deg":"200","wind_dir":"西南风","wind_sc":"微风","wind_spd":"6"},{"cond_code_d":"100","cond_code_n":"100","cond_txt_d":"晴","cond_txt_n":"晴","date":"2018-02-05","hum":"20","mr":"22:55","ms":"10:09","pcpn":"0.0","pop":"0","pres":"1035","sr":"07:14","ss":"17:36","tmp_max":"0","tmp_min":"-8","uv_index":"2","vis":"20","wind_deg":"354","wind_dir":"北风","wind_sc":"4-5","wind_spd":"23"},{"cond_code_d":"100","cond_code_n":"101","cond_txt_d":"晴","cond_txt_n":"多云","date":"2018-02-06","hum":"18","mr":"23:56","ms":"10:40","pcpn":"0.0","pop":"0","pres":"1029","sr":"07:13","ss":"17:37","tmp_max":"2","tmp_min":"-6","uv_index":"2","vis":"20","wind_deg":"252","wind_dir":"西南风","wind_sc":"微风","wind_spd":"6"},{"cond_code_d":"100","cond_code_n":"100","cond_txt_d":"晴","cond_txt_n":"晴","date":"2018-02-07","hum":"21","mr":"04:45","ms":"11:12","pcpn":"0.0","pop":"0","pres":"1028","sr":"07:12","ss":"17:39","tmp_max":"2","tmp_min":"-7","uv_index":"2","vis":"20","wind_deg":"288","wind_dir":"西北风","wind_sc":"4-5","wind_spd":"24"},{"cond_code_d":"100","cond_code_n":"100","cond_txt_d":"晴","cond_txt_n":"晴","date":"2018-02-08","hum":"19","mr":"00:55","ms":"11:46","pcpn":"0.0","pop":"0","pres":"1023","sr":"07:11","ss":"17:40","tmp_max":"3","tmp_min":"-5","uv_index":"2","vis":"20","wind_deg":"217","wind_dir":"西南风","wind_sc":"3-4","wind_spd":"17"},{"cond_code_d":"101","cond_code_n":"101","cond_txt_d":"多云","cond_txt_n":"多云","date":"2018-02-09","hum":"17","mr":"01:53","ms":"12:21","pcpn":"0.0","pop":"0","pres":"1023","sr":"07:10","ss":"17:41","tmp_max":"4","tmp_min":"-4","uv_index":"2","vis":"20","wind_deg":"303","wind_dir":"西北风","wind_sc":"4-5","wind_spd":"19"},{"cond_code_d":"100","cond_code_n":"100","cond_txt_d":"晴","cond_txt_n":"晴","date":"2018-02-10","hum":"14","mr":"02:48","ms":"13:01","pcpn":"0.0","pop":"0","pres":"1025","sr":"07:08","ss":"17:42","tmp_max":"0","tmp_min":"-6","uv_index":"2","vis":"20","wind_deg":"98","wind_dir":"东风","wind_sc":"微风","wind_spd":"3"}]
         * hourly : [{"cloud":"12","cond_code":"103","cond_txt":"晴间多云","dew":"-22","hum":"18","pop":"0","pres":"1034","time":"2018-02-04 13:00","tmp":"0","wind_deg":"330","wind_dir":"西北风","wind_sc":"3-4","wind_spd":"19"},{"cloud":"11","cond_code":"103","cond_txt":"晴间多云","dew":"-21","hum":"19","pop":"0","pres":"1033","time":"2018-02-04 16:00","tmp":"0","wind_deg":"324","wind_dir":"西北风","wind_sc":"微风","wind_spd":"11"},{"cloud":"10","cond_code":"103","cond_txt":"晴间多云","dew":"-20","hum":"22","pop":"0","pres":"1035","time":"2018-02-04 19:00","tmp":"0","wind_deg":"248","wind_dir":"西南风","wind_sc":"微风","wind_spd":"4"},{"cloud":"11","cond_code":"103","cond_txt":"晴间多云","dew":"-20","hum":"26","pop":"0","pres":"1036","time":"2018-02-04 22:00","tmp":"0","wind_deg":"247","wind_dir":"西南风","wind_sc":"微风","wind_spd":"4"},{"cloud":"35","cond_code":"103","cond_txt":"晴间多云","dew":"-21","hum":"26","pop":"0","pres":"1034","time":"2018-02-05 01:00","tmp":"-3","wind_deg":"115","wind_dir":"东南风","wind_sc":"微风","wind_spd":"10"},{"cloud":"12","cond_code":"103","cond_txt":"晴间多云","dew":"-20","hum":"30","pop":"0","pres":"1034","time":"2018-02-05 04:00","tmp":"-4","wind_deg":"14","wind_dir":"东北风","wind_sc":"微风","wind_spd":"12"},{"cloud":"11","cond_code":"103","cond_txt":"晴间多云","dew":"-21","hum":"31","pop":"0","pres":"1036","time":"2018-02-05 07:00","tmp":"-4","wind_deg":"10","wind_dir":"北风","wind_sc":"微风","wind_spd":"12"},{"cloud":"9","cond_code":"103","cond_txt":"晴间多云","dew":"-23","hum":"23","pop":"0","pres":"1037","time":"2018-02-05 10:00","tmp":"-2","wind_deg":"240","wind_dir":"西南风","wind_sc":"3-4","wind_spd":"17"}]
         * lifestyle : [{"brf":"较不舒适","txt":"白天天气晴好，但仍会使您感觉偏冷，不很舒适，请注意适时添加衣物，以防感冒。","type":"comf"},{"brf":"冷","txt":"天气冷，建议着棉服、羽绒服、皮夹克加羊毛衫等冬季服装。年老体弱者宜着厚棉衣、冬大衣或厚羽绒服。","type":"drsg"},{"brf":"易发","txt":"天冷风大，易发生感冒，请注意适当增加衣服，加强自我防护避免感冒。","type":"flu"},{"brf":"较不宜","txt":"天气较好，但考虑天气寒冷，推荐您进行室内运动，户外运动时请注意保暖并做好准备活动。","type":"sport"},{"brf":"较适宜","txt":"天气较好，同时又有微风伴您一路同行。稍冷，较适宜旅游，您仍可陶醉于大自然的美丽风光中。","type":"trav"},{"brf":"弱","txt":"紫外线强度较弱，建议出门前涂擦SPF在12-15之间、PA+的防晒护肤品。","type":"uv"},{"brf":"较不宜","txt":"较不宜洗车，未来一天无雨，风力较大，如果执意擦洗汽车，要做好蒙上污垢的心理准备。","type":"cw"},{"brf":"中","txt":"气象条件对空气污染物稀释、扩散和清除无明显影响，易感人群应适当减少室外活动时间。","type":"air"}]
         */

        private BasicBean basic;
        private UpdateBean update;
        private String status;
        private NowBean now;
        private List<DailyForecastBean> daily_forecast;
        private List<HourlyBean> hourly;
        private List<LifestyleBean> lifestyle;

        public BasicBean getBasic() {
            return basic;
        }

        public void setBasic(BasicBean basic) {
            this.basic = basic;
        }

        public UpdateBean getUpdate() {
            return update;
        }

        public void setUpdate(UpdateBean update) {
            this.update = update;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public NowBean getNow() {
            return now;
        }

        public void setNow(NowBean now) {
            this.now = now;
        }

        public List<DailyForecastBean> getDaily_forecast() {
            return daily_forecast;
        }

        public void setDaily_forecast(List<DailyForecastBean> daily_forecast) {
            this.daily_forecast = daily_forecast;
        }

        public List<HourlyBean> getHourly() {
            return hourly;
        }

        public void setHourly(List<HourlyBean> hourly) {
            this.hourly = hourly;
        }

        public List<LifestyleBean> getLifestyle() {
            return lifestyle;
        }

        public void setLifestyle(List<LifestyleBean> lifestyle) {
            this.lifestyle = lifestyle;
        }

        public static class BasicBean {
            /**
             * cid : CN101030100
             * location : 天津
             * parent_city : 天津
             * admin_area : 天津
             * cnty : 中国
             * lat : 39.12559509
             * lon : 117.19018555
             * tz : +8.0
             */

            private String cid;
            private String location;
            private String parent_city;
            private String admin_area;
            private String cnty;
            private String lat;
            private String lon;
            private String tz;

            public String getCid() {
                return cid;
            }

            public void setCid(String cid) {
                this.cid = cid;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public String getParent_city() {
                return parent_city;
            }

            public void setParent_city(String parent_city) {
                this.parent_city = parent_city;
            }

            public String getAdmin_area() {
                return admin_area;
            }

            public void setAdmin_area(String admin_area) {
                this.admin_area = admin_area;
            }

            public String getCnty() {
                return cnty;
            }

            public void setCnty(String cnty) {
                this.cnty = cnty;
            }

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }

            public String getLon() {
                return lon;
            }

            public void setLon(String lon) {
                this.lon = lon;
            }

            public String getTz() {
                return tz;
            }

            public void setTz(String tz) {
                this.tz = tz;
            }
        }

        public static class UpdateBean {
            /**
             * loc : 2018-02-04 12:51
             * utc : 2018-02-04 04:51
             */

            private String loc;
            private String utc;

            public String getLoc() {
                return loc;
            }

            public void setLoc(String loc) {
                this.loc = loc;
            }

            public String getUtc() {
                return utc;
            }

            public void setUtc(String utc) {
                this.utc = utc;
            }
        }

        public static class NowBean {
            /**
             * cloud : 24
             * cond_code : 100
             * cond_txt : 晴
             * fl : -7
             * hum : 18
             * pcpn : 0.0
             * pres : 1036
             * tmp : 1
             * vis : 10
             * wind_deg : 225
             * wind_dir : 西南风
             * wind_sc : 微风
             * wind_spd : 9
             */

            private String cloud;
            private int cond_code;
            private String cond_txt;
            private String fl;
            private String hum;
            private String pcpn;
            private String pres;
            private String tmp;
            private String vis;
            private String wind_deg;
            private String wind_dir;
            private String wind_sc;
            private String wind_spd;

            public String getCloud() {
                return cloud;
            }

            public void setCloud(String cloud) {
                this.cloud = cloud;
            }

            public int getCond_code() {
                return cond_code;
            }

            public void setCond_code(int cond_code) {
                this.cond_code = cond_code;
            }

            public String getCond_txt() {
                return cond_txt;
            }

            public void setCond_txt(String cond_txt) {
                this.cond_txt = cond_txt;
            }

            public String getFl() {
                return fl;
            }

            public void setFl(String fl) {
                this.fl = fl;
            }

            public String getHum() {
                return hum;
            }

            public void setHum(String hum) {
                this.hum = hum;
            }

            public String getPcpn() {
                return pcpn;
            }

            public void setPcpn(String pcpn) {
                this.pcpn = pcpn;
            }

            public String getPres() {
                return pres;
            }

            public void setPres(String pres) {
                this.pres = pres;
            }

            public String getTmp() {
                return tmp;
            }

            public void setTmp(String tmp) {
                this.tmp = tmp;
            }

            public String getVis() {
                return vis;
            }

            public void setVis(String vis) {
                this.vis = vis;
            }

            public String getWind_deg() {
                return wind_deg;
            }

            public void setWind_deg(String wind_deg) {
                this.wind_deg = wind_deg;
            }

            public String getWind_dir() {
                return wind_dir;
            }

            public void setWind_dir(String wind_dir) {
                this.wind_dir = wind_dir;
            }

            public String getWind_sc() {
                return wind_sc;
            }

            public void setWind_sc(String wind_sc) {
                this.wind_sc = wind_sc;
            }

            public String getWind_spd() {
                return wind_spd;
            }

            public void setWind_spd(String wind_spd) {
                this.wind_spd = wind_spd;
            }
        }

        public static class DailyForecastBean {
            /**
             * cond_code_d : 100
             * cond_code_n : 100
             * cond_txt_d : 晴
             * cond_txt_n : 晴
             * date : 2018-02-04
             * hum : 21
             * mr : 21:52
             * ms : 09:37
             * pcpn : 0.0
             * pop : 0
             * pres : 1034
             * sr : 07:15
             * ss : 17:35
             * tmp_max : 2
             * tmp_min : -6
             * uv_index : 2
             * vis : 20
             * wind_deg : 200
             * wind_dir : 西南风
             * wind_sc : 微风
             * wind_spd : 6
             */

            private int cond_code_d;
            private int cond_code_n;
            private String cond_txt_d;
            private String cond_txt_n;
            private String date;
            private String hum;
            private String mr;
            private String ms;
            private String pcpn;
            private String pop;
            private String pres;
            private String sr;
            private String ss;
            private String tmp_max;
            private String tmp_min;
            private String uv_index;
            private String vis;
            private String wind_deg;
            private String wind_dir;
            private String wind_sc;
            private String wind_spd;

            public int getCond_code_d() {
                return cond_code_d;
            }

            public void setCond_code_d(int cond_code_d) {
                this.cond_code_d = cond_code_d;
            }

            public int getCond_code_n() {
                return cond_code_n;
            }

            public void setCond_code_n(int cond_code_n) {
                this.cond_code_n = cond_code_n;
            }

            public String getCond_txt_d() {
                return cond_txt_d;
            }

            public void setCond_txt_d(String cond_txt_d) {
                this.cond_txt_d = cond_txt_d;
            }

            public String getCond_txt_n() {
                return cond_txt_n;
            }

            public void setCond_txt_n(String cond_txt_n) {
                this.cond_txt_n = cond_txt_n;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getHum() {
                return hum;
            }

            public void setHum(String hum) {
                this.hum = hum;
            }

            public String getMr() {
                return mr;
            }

            public void setMr(String mr) {
                this.mr = mr;
            }

            public String getMs() {
                return ms;
            }

            public void setMs(String ms) {
                this.ms = ms;
            }

            public String getPcpn() {
                return pcpn;
            }

            public void setPcpn(String pcpn) {
                this.pcpn = pcpn;
            }

            public String getPop() {
                return pop;
            }

            public void setPop(String pop) {
                this.pop = pop;
            }

            public String getPres() {
                return pres;
            }

            public void setPres(String pres) {
                this.pres = pres;
            }

            public String getSr() {
                return sr;
            }

            public void setSr(String sr) {
                this.sr = sr;
            }

            public String getSs() {
                return ss;
            }

            public void setSs(String ss) {
                this.ss = ss;
            }

            public String getTmp_max() {
                return tmp_max;
            }

            public void setTmp_max(String tmp_max) {
                this.tmp_max = tmp_max;
            }

            public String getTmp_min() {
                return tmp_min;
            }

            public void setTmp_min(String tmp_min) {
                this.tmp_min = tmp_min;
            }

            public String getUv_index() {
                return uv_index;
            }

            public void setUv_index(String uv_index) {
                this.uv_index = uv_index;
            }

            public String getVis() {
                return vis;
            }

            public void setVis(String vis) {
                this.vis = vis;
            }

            public String getWind_deg() {
                return wind_deg;
            }

            public void setWind_deg(String wind_deg) {
                this.wind_deg = wind_deg;
            }

            public String getWind_dir() {
                return wind_dir;
            }

            public void setWind_dir(String wind_dir) {
                this.wind_dir = wind_dir;
            }

            public String getWind_sc() {
                return wind_sc;
            }

            public void setWind_sc(String wind_sc) {
                this.wind_sc = wind_sc;
            }

            public String getWind_spd() {
                return wind_spd;
            }

            public void setWind_spd(String wind_spd) {
                this.wind_spd = wind_spd;
            }
        }

        public static class HourlyBean {
            /**
             * cloud : 12
             * cond_code : 103
             * cond_txt : 晴间多云
             * dew : -22
             * hum : 18
             * pop : 0
             * pres : 1034
             * time : 2018-02-04 13:00
             * tmp : 0
             * wind_deg : 330
             * wind_dir : 西北风
             * wind_sc : 3-4
             * wind_spd : 19
             */

            private String cloud;
            private String cond_code;
            private String cond_txt;
            private String dew;
            private String hum;
            private String pop;
            private String pres;
            private String time;
            private String tmp;
            private String wind_deg;
            private String wind_dir;
            private String wind_sc;
            private String wind_spd;

            public String getCloud() {
                return cloud;
            }

            public void setCloud(String cloud) {
                this.cloud = cloud;
            }

            public String getCond_code() {
                return cond_code;
            }

            public void setCond_code(String cond_code) {
                this.cond_code = cond_code;
            }

            public String getCond_txt() {
                return cond_txt;
            }

            public void setCond_txt(String cond_txt) {
                this.cond_txt = cond_txt;
            }

            public String getDew() {
                return dew;
            }

            public void setDew(String dew) {
                this.dew = dew;
            }

            public String getHum() {
                return hum;
            }

            public void setHum(String hum) {
                this.hum = hum;
            }

            public String getPop() {
                return pop;
            }

            public void setPop(String pop) {
                this.pop = pop;
            }

            public String getPres() {
                return pres;
            }

            public void setPres(String pres) {
                this.pres = pres;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getTmp() {
                return tmp;
            }

            public void setTmp(String tmp) {
                this.tmp = tmp;
            }

            public String getWind_deg() {
                return wind_deg;
            }

            public void setWind_deg(String wind_deg) {
                this.wind_deg = wind_deg;
            }

            public String getWind_dir() {
                return wind_dir;
            }

            public void setWind_dir(String wind_dir) {
                this.wind_dir = wind_dir;
            }

            public String getWind_sc() {
                return wind_sc;
            }

            public void setWind_sc(String wind_sc) {
                this.wind_sc = wind_sc;
            }

            public String getWind_spd() {
                return wind_spd;
            }

            public void setWind_spd(String wind_spd) {
                this.wind_spd = wind_spd;
            }
        }

        public static class LifestyleBean {
            /**
             * brf : 较不舒适
             * txt : 白天天气晴好，但仍会使您感觉偏冷，不很舒适，请注意适时添加衣物，以防感冒。
             * type : comf
             */

            private String brf;
            private String txt;
            private String type;

            public String getBrf() {
                return brf;
            }

            public void setBrf(String brf) {
                this.brf = brf;
            }

            public String getTxt() {
                return txt;
            }

            public void setTxt(String txt) {
                this.txt = txt;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }
    }
}

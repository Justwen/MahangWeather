package com.mahang.weather.common;

import com.mahang.weather.R;

interface WeatherResource {
	
	enum Weather{
		Sunny,
		PartlySunny,
		MostlyCloudy,
		Shower,
		Thunderstorms,
		Hail,
	}
	
	static final int[] WEATHER_ICON_D = new int[]{
		R.drawable.weather_ic_sunny,    // 0   ��
		R.drawable.weather_ic_partlysunny, // 1  ����
		R.drawable.weather_ic_mostlycloudy, // 2  ��
		R.drawable.weather_ic_shower, // 3  ����
		R.drawable.weather_ic_thunderstorms, // 4  ������
		R.drawable.weather_ic_hail, //  5 ��������б���
		R.drawable.weather_ic_rainandsnowmixed, // 6  ���ѩ
		R.drawable.weather_ic_rain, // 7 С��,����
		R.drawable.weather_ic_heavy_rain, // 8 ���꣬����
		R.drawable.weather_ic_flurries, // 9  ��ѩ Сѩ ��ѩ
		R.drawable.weather_ic_snow, // 10 ��ѩ ��ѩ
		R.drawable.weather_ic_fog, // 11 �� ����
		R.drawable.weather_ic_sand_storm, //12 ɳ����
		R.drawable.weather_ic_ice, // 13 ����
		R.drawable.weather_ic_hot, //  14 ����
		R.drawable.weather_ic_cold, // 15 ����
		R.drawable.weather_ic_windy // 16 �з�
	};
	
	
	static final int[] WEATHER_ICON_N = new int[]{
		R.drawable.weather_ic_clear,    // 0   ��
		R.drawable.weather_ic_mostlyclear, // 1  ����
		R.drawable.weather_ic_mostlycloudy, // 2  ��
		R.drawable.weather_ic_shower, // 3  ����
		R.drawable.weather_ic_thunderstorms, // 4  ������
		R.drawable.weather_ic_hail, //  5 ��������б���
		R.drawable.weather_ic_rainandsnowmixed, // 6  ���ѩ
		R.drawable.weather_ic_rain, // 7 С��,����
		R.drawable.weather_ic_heavy_rain, // 8 ���꣬����
		R.drawable.weather_ic_flurries, // 9  ��ѩ Сѩ ��ѩ
		R.drawable.weather_ic_snow, // 10 ��ѩ ��ѩ
		R.drawable.weather_ic_fog, // 11 �� ����
		R.drawable.weather_ic_sand_storm, //12 ɳ����
		R.drawable.weather_ic_ice, // 13 ����
		R.drawable.weather_ic_hot, //  14 ����
		R.drawable.weather_ic_cold, // 15 ����
		R.drawable.weather_ic_windy // 16 �з�
	};
	
	static final int[] WEATHER_BG_D = new int[]{
		R.drawable.weather_bg_sunny,    // 0   ��
		R.drawable.weather_bg_partlysunny, // 1  ����
		R.drawable.weather_bg_mostlycloudy_day, // 2  ��
		R.drawable.weather_bg_shower_day, // 3  ����
		R.drawable.weather_bg_thunderstorms_day, // 4  ������
		R.drawable.weather_bg_hail_day, //  5 ��������б���
		R.drawable.weather_bg_rainandsnowmixed_day, // 6  ���ѩ
		R.drawable.weather_bg_rain_day, // 7 С��,����
		R.drawable.weather_bg_heavy_rain_day, // 8 ���꣬����
		R.drawable.weather_bg_flurries_day, // 9  ��ѩ Сѩ ��ѩ
		R.drawable.weather_bg_snow_day, // 10 ��ѩ ��ѩ
		R.drawable.weather_bg_fog_day, // 11 �� ����
		R.drawable.weather_bg_sand_storm_day, //12 ɳ����
		R.drawable.weather_bg_ice, // 13 ����
		R.drawable.weather_bg_hot, //  14 ����
		R.drawable.weather_bg_cold, // 15 ����
		R.drawable.weather_bg_windy_day // 16 �з�
	};
	
	static final int[] WEATHER_BG_N = new int[]{
		R.drawable.weather_bg_clear,    // 0   ��
		R.drawable.weather_bg_mostlyclear, // 1  ����
		R.drawable.weather_bg_mostlycloudy_night, // 2  ��
		R.drawable.weather_bg_shower_night, // 3  ����
		R.drawable.weather_bg_thunderstorms_night, // 4  ������
		R.drawable.weather_bg_hail_night, //  5 ��������б��� 
		R.drawable.weather_bg_rainandsnowmixed_night, // 6  ���ѩ
		R.drawable.weather_bg_rain_night, // 7 С��,����
		R.drawable.weather_bg_heavy_rain_night, // 8 ���꣬����
		R.drawable.weather_bg_flurries_night, // 9  ��ѩ Сѩ ��ѩ 
		R.drawable.weather_bg_snow_night, // 10 ��ѩ ��ѩ
		R.drawable.weather_bg_fog_night, // 11 �� ����
		R.drawable.weather_bg_sand_storm_night, //12 ɳ����
		R.drawable.weather_bg_ice, // 13 ����
		R.drawable.weather_bg_hot, //  14 ����
		R.drawable.weather_bg_cold, // 15 ����
		R.drawable.weather_bg_windy_night // 16 �з�
	};
	
	static final int[] STATUS_BAR_COLOR_D = new int[]{
		R.color.startColor_sunny,    // 0   ��
		R.color.startColor_partlysunny, // 1  ����
		R.color.startColor_mostlycloudy_day, // 2  ��
		R.color.startColor_shower_day, // 3  ����
		R.color.startColor_thunderstorms_day, // 4  ������
		R.color.startColor_hail_day, //  5 ��������б���
		R.color.startColor_rainandsnowmixed_day, // 6  ���ѩ
		R.color.startColor_rain_day, // 7 С��,����
		R.color.startColor_heavy_rain_day, // 8 ���꣬����
		R.color.startColor_flurries_day, // 9  ��ѩ Сѩ ��ѩ
		R.color.startColor_snow_day, // 10 ��ѩ ��ѩ
		R.color.startColor_fog_day, // 11 �� ����
		R.color.startColor_sand_storm_day, //12 ɳ����
		R.color.startColor_ice, // 13 ����
		R.color.startColor_hot, //  14 ����
		R.color.startColor_cold, // 15 ����	
		R.color.startColor_windy_day
	};
	
	static final int[] STATUS_BAR_COLOR_N = new int[]{
		R.color.startColor_clear,    // 0   ��
		R.color.startColor_mostlyclear, // 1  ����
		R.color.startColor_mostlycloudy_night, // 2  ��
		R.color.startColor_shower_night, // 3  ����
		R.color.startColor_thunderstorms_night, // 4  ������
		R.color.startColor_hail_night, //  5 ��������б��� 
		R.color.startColor_rainandsnowmixed_night, // 6  ���ѩ
		R.color.startColor_rain_night, // 7 С��,����
		R.color.startColor_heavy_rain_night, // 8 ���꣬����
		R.color.startColor_flurries_night, // 9  ��ѩ Сѩ ��ѩ 
		R.color.startColor_snow_night, // 10 ��ѩ ��ѩ
		R.color.startColor_fog_night, // 11 �� ����
		R.color.startColor_sand_storm_night, //12 ɳ����
		R.color.startColor_ice, // 13 ����
		R.color.startColor_hot, //  14 ����
		R.color.startColor_cold, // 15 ����
		R.color.startColor_windy_night // 16 �з�	
	};


}

package com.protoplus.newscircle;

import android.app.Application;
import android.graphics.Typeface;

import com.protoplus.newscircle.Bean.SportChildSceneBean;
import com.protoplus.newscircle.Util.Helper;

import java.util.ArrayList;


public class ContextData extends Application{
	private Typeface LatoBlack;
	public Typeface getLatoBlack() {
		return LatoBlack;
	}


	@Override
	public void onCreate() {
		super.onCreate();
		Helper.fromReceiver = false;
		LatoBlack = Typeface.createFromAsset(getAssets(), Helper.LatoBlack);
	}

	/*public static ArrayList<SportChildSceneBean> getHeadline_bean_array() {
		return ContextData.headline_bean_array;
	}

	public static void setHeadline_bean_array(ArrayList<SportChildSceneBean> headline_bean_array) {
		ContextData.headline_bean_array = headline_bean_array;
	}*/
	//private static ArrayList<SportChildSceneBean> headline_bean_array;
	public static ArrayList<SportChildSceneBean> getIE_bean_array() {
		return IE_bean_array;
	}

	public static void setIE_bean_array(ArrayList<SportChildSceneBean> IE_bean_array) {
		ContextData.IE_bean_array = IE_bean_array;
	}

	private static ArrayList<SportChildSceneBean> IE_bean_array;

	public static ArrayList<SportChildSceneBean> getHT_bean_array() {
		return HT_bean_array;
	}

	public static void setHT_bean_array(ArrayList<SportChildSceneBean> HT_bean_array) {
		ContextData.HT_bean_array = HT_bean_array;
	}

	private static ArrayList<SportChildSceneBean> HT_bean_array;

	public static ArrayList<SportChildSceneBean> getOneIndia_bean_array() {
		return OneIndia_bean_array;
	}

	public static void setOneIndia_bean_array(ArrayList<SportChildSceneBean> oneIndia_bean_array) {
		OneIndia_bean_array = oneIndia_bean_array;
	}

	private static ArrayList<SportChildSceneBean> OneIndia_bean_array;

	public static ArrayList<SportChildSceneBean> getTimesOfIndia_bean_array() {
		return TimesOfIndia_bean_array;
	}

	public static void setTimesOfIndia_bean_array(ArrayList<SportChildSceneBean> timesOfIndia_bean_array) {
		TimesOfIndia_bean_array = timesOfIndia_bean_array;
	}

	private static ArrayList<SportChildSceneBean> TimesOfIndia_bean_array;

	public static ArrayList<SportChildSceneBean> getIndiaTV_bean_array() {
		return IndiaTV_bean_array;
	}

	public static void setIndiaTV_bean_array(ArrayList<SportChildSceneBean> indiaTV_bean_array) {
		IndiaTV_bean_array = indiaTV_bean_array;
	}

	private static ArrayList<SportChildSceneBean> IndiaTV_bean_array;

	public static ArrayList<SportChildSceneBean> getIBNLive_bean_array() {
		return IBNLive_bean_array;
	}

	public static void setIBNLive_bean_array(ArrayList<SportChildSceneBean> IBNLive_bean_array) {
		ContextData.IBNLive_bean_array = IBNLive_bean_array;
	}

	private static ArrayList<SportChildSceneBean> IBNLive_bean_array;

	public static ArrayList<SportChildSceneBean> getIndiaToday_bean_array() {
		return IndiaToday_bean_array;
	}

	public static void setIndiaToday_bean_array(ArrayList<SportChildSceneBean> indiaToday_bean_array) {
		IndiaToday_bean_array = indiaToday_bean_array;
	}

	private static ArrayList<SportChildSceneBean> IndiaToday_bean_array;

	public static ArrayList<SportChildSceneBean> getDeccanHerald_bean_array() {
		return DeccanHerald_bean_array;
	}

	public static void setDeccanHerald_bean_array(ArrayList<SportChildSceneBean> deccanHerald_bean_array) {
		DeccanHerald_bean_array = deccanHerald_bean_array;
	}

	private static ArrayList<SportChildSceneBean> DeccanHerald_bean_array;

	public static ArrayList<SportChildSceneBean> getTheStatesMan_bean_array() {
		return TheStatesMan_bean_array;
	}

	public static void setTheStatesMan_bean_array(ArrayList<SportChildSceneBean> theStatesMan_bean_array) {
		TheStatesMan_bean_array = theStatesMan_bean_array;
	}

	private static ArrayList<SportChildSceneBean> TheStatesMan_bean_array;

	public static ArrayList<SportChildSceneBean> getLiveMint_bean_array() {
		return LiveMint_bean_array;
	}

	public static void setLiveMint_bean_array(ArrayList<SportChildSceneBean> liveMint_bean_array) {
		LiveMint_bean_array = liveMint_bean_array;
	}

	private static ArrayList<SportChildSceneBean> LiveMint_bean_array;

	public static ArrayList<SportChildSceneBean> getMyDigitalFC_bean_array() {
		return MyDigitalFC_bean_array;
	}

	public static void setMyDigitalFC_bean_array(ArrayList<SportChildSceneBean> myDigitalFC_bean_array) {
		MyDigitalFC_bean_array = myDigitalFC_bean_array;
	}

	private static ArrayList<SportChildSceneBean> MyDigitalFC_bean_array;

	public static ArrayList<SportChildSceneBean> getAsiaAge_bean_array() {
		return AsiaAge_bean_array;
	}

	public static void setAsiaAge_bean_array(ArrayList<SportChildSceneBean> asiaAge_bean_array) {
		AsiaAge_bean_array = asiaAge_bean_array;
	}

	private static ArrayList<SportChildSceneBean> AsiaAge_bean_array;

	public static ArrayList<SportChildSceneBean> getFirstPost_bean_array() {
		return FirstPost_bean_array;
	}

	public static void setFirstPost_bean_array(ArrayList<SportChildSceneBean> firstPost_bean_array) {
		FirstPost_bean_array = firstPost_bean_array;
	}

	private static ArrayList<SportChildSceneBean> FirstPost_bean_array;

	public static ArrayList<SportChildSceneBean> getRediff_bean_array() {
		return Rediff_bean_array;
	}

	public static void setRediff_bean_array(ArrayList<SportChildSceneBean> rediff_bean_array) {
		Rediff_bean_array = rediff_bean_array;
	}

	private static ArrayList<SportChildSceneBean> Rediff_bean_array;

	public static ArrayList<SportChildSceneBean> getMoneyControl_bean_array() {
		return MoneyControl_bean_array;
	}

	public static void setMoneyControl_bean_array(ArrayList<SportChildSceneBean> moneyControl_bean_array) {
		MoneyControl_bean_array = moneyControl_bean_array;
	}

	private static ArrayList<SportChildSceneBean> MoneyControl_bean_array;

	public static ArrayList<SportChildSceneBean> getEconomicsTimes_bean_array() {
		return EconomicsTimes_bean_array;
	}

	public static void setEconomicsTimes_bean_array(ArrayList<SportChildSceneBean> economicsTimes_bean_array) {
		EconomicsTimes_bean_array = economicsTimes_bean_array;
	}

	private static ArrayList<SportChildSceneBean> EconomicsTimes_bean_array;

	public static ArrayList<SportChildSceneBean> getHinduBusinessLine_bean_array() {
		return HinduBusinessLine_bean_array;
	}

	public static void setHinduBusinessLine_bean_array(ArrayList<SportChildSceneBean> hinduBusinessLine_bean_array) {
		HinduBusinessLine_bean_array = hinduBusinessLine_bean_array;
	}

	private static ArrayList<SportChildSceneBean> HinduBusinessLine_bean_array;

	public static ArrayList<SportChildSceneBean> getCNN_IBN_bean_array() {
		return CNN_IBN_bean_array;
	}

	public static void setCNN_IBN_bean_array(ArrayList<SportChildSceneBean> CNN_IBN_bean_array) {
		ContextData.CNN_IBN_bean_array = CNN_IBN_bean_array;
	}

	private static ArrayList<SportChildSceneBean> CNN_IBN_bean_array;

	public static ArrayList<SportChildSceneBean> getDNA_India_bean_array() {
		return DNA_India_bean_array;
	}

	public static void setDNA_India_bean_array(ArrayList<SportChildSceneBean> DNA_India_bean_array) {
		ContextData.DNA_India_bean_array = DNA_India_bean_array;
	}

	private static ArrayList<SportChildSceneBean> DNA_India_bean_array;

	public static ArrayList<SportChildSceneBean> getDeccanChronicle_bean_array() {
		return DeccanChronicle_bean_array;
	}

	public static void setDeccanChronicle_bean_array(ArrayList<SportChildSceneBean> deccanChronicle_bean_array) {
		DeccanChronicle_bean_array = deccanChronicle_bean_array;
	}

	private static ArrayList<SportChildSceneBean> DeccanChronicle_bean_array;

	public static ArrayList<SportChildSceneBean> getCNET_bean_array() {
		return CNET_bean_array;
	}

	public static void setCNET_bean_array(ArrayList<SportChildSceneBean> CNET_bean_array) {
		ContextData.CNET_bean_array = CNET_bean_array;
	}

	private static ArrayList<SportChildSceneBean> CNET_bean_array;

	public static ArrayList<SportChildSceneBean> getTechCrunchNewsFragment_bean_array() {
		return TechCrunchNewsFragment_bean_array;
	}

	public static void setTechCrunchNewsFragment_bean_array(ArrayList<SportChildSceneBean> techCrunchNewsFragment_bean_array) {
		TechCrunchNewsFragment_bean_array = techCrunchNewsFragment_bean_array;
	}

	private static ArrayList<SportChildSceneBean> TechCrunchNewsFragment_bean_array;

	public static ArrayList<SportChildSceneBean> getInfoWorldNewsFragment_bean_array() {
		return InfoWorldNewsFragment_bean_array;
	}

	public static void setInfoWorldNewsFragment_bean_array(ArrayList<SportChildSceneBean> infoWorldNewsFragment_bean_array) {
		InfoWorldNewsFragment_bean_array = infoWorldNewsFragment_bean_array;
	}

	private static ArrayList<SportChildSceneBean> InfoWorldNewsFragment_bean_array;

	public static ArrayList<SportChildSceneBean> getTechRepublic_bean_array() {
		return TechRepublic_bean_array;
	}

	public static void setTechRepublic_bean_array(ArrayList<SportChildSceneBean> techRepublic_bean_array) {
		TechRepublic_bean_array = techRepublic_bean_array;
	}

	private static ArrayList<SportChildSceneBean> TechRepublic_bean_array;

	public static ArrayList<SportChildSceneBean> getWired_bean_array() {
		return Wired_bean_array;
	}

	public static void setWired_bean_array(ArrayList<SportChildSceneBean> wired_bean_array) {
		Wired_bean_array = wired_bean_array;
	}

	private static ArrayList<SportChildSceneBean> Wired_bean_array;

	public static ArrayList<SportChildSceneBean> getOutLookIndia_bean_array() {
		return OutLookIndia_bean_array;
	}

	public static void setOutLookIndia_bean_array(ArrayList<SportChildSceneBean> outLookIndia_bean_array) {
		OutLookIndia_bean_array = outLookIndia_bean_array;
	}

	private static ArrayList<SportChildSceneBean> OutLookIndia_bean_array;

	public static ArrayList<SportChildSceneBean> getMoneyLife_bean_array() {
		return MoneyLife_bean_array;
	}

	public static void setMoneyLife_bean_array(ArrayList<SportChildSceneBean> moneyLife_bean_array) {
		MoneyLife_bean_array = moneyLife_bean_array;
	}

	private static ArrayList<SportChildSceneBean> MoneyLife_bean_array;
	public static ArrayList<ArrayList<SportChildSceneBean>> getMain_ar() {
		return main_ar;
	}

	public static void setMain_ar(ArrayList<ArrayList<SportChildSceneBean>> main_ar) {
		ContextData.main_ar = main_ar;
	}

	private static ArrayList<ArrayList<SportChildSceneBean>> main_ar;

	public static int getPosition() {
		return position;
	}

	public static void setPosition(int position) {
		ContextData.position = position;
	}

	private static int position;


}
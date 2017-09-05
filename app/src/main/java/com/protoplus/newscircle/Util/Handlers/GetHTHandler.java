package com.protoplus.newscircle.Util.Handlers;

import com.protoplus.newscircle.Bean.SportChildSceneBean;
import com.protoplus.newscircle.Util.Helper;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by Aakash on 9/26/2015.
 */
public class GetHTHandler extends DefaultHandler {
    public boolean title=false,pubDate=false,link=false , description=false,image_link=false;
    public String descriptionText = "",image_linkText="";
    public SportChildSceneBean bean;
    public String titleText="",postIdText="";
    public int counter = 0,i=0;
    public int limit = 0;
    public String image_url = null;
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        /*if(limit< Helper.minValue){
            if(qName.equals("item"))
            {
                limit++;
                return;
            }
        }*/
        if (qName.equals("item")){
            image_url = "";
            bean = new SportChildSceneBean();
        }
        else if(qName.equals("enclosure") && bean!=null){
            //image_url=image_url+"#"+attributes.getValue(null, "url");
            image_linkText="";
            image_link=true;
            //bean.setMainImageLink(image_url);
        }

        else if (qName.equals("title") && bean!=null){
            titleText="";
            title = true;
        }

        else if (qName.equals("pubDate") && bean!=null) pubDate = true;

        else if(qName.equals("link") && bean!=null) {
            postIdText = "";
            link = true;
        }
        else if(qName.equals("description") && bean!=null){
            description =true;
            descriptionText = "";
        }


    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);

        if (title  && bean!=null){
            titleText = titleText + new String(ch, start, length);
            bean.setTitle(titleText);
        }
        else if (pubDate  && bean!=null) {
            String text=new String(ch,start,length);
            if(text.length()>10){
                text = text.substring(0,text.length()-6);
            }
            bean.setPubDate(text);
        }
        else if(link && bean != null){
            postIdText=postIdText+new String(ch, start, length);
            bean.setPostId(postIdText);
        }
        else if(description && bean !=null){
                String text=new String(ch,start,length);
                descriptionText = descriptionText + text;
                bean.setDescription(descriptionText);
        }
        else if(image_link && bean !=null){
            image_linkText = image_linkText + new String(ch,start,length);
            bean.setImageLink(image_linkText);
            bean.setMainImageLink(image_linkText);
        }

    }

}

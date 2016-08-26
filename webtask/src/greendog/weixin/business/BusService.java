package greendog.weixin.business;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.directwebremoting.json.types.JsonArray;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import greendog.weixin.beans.bus.Bus;
import greendog.weixin.beans.bus.Data;
import greendog.weixin.beans.bus.Station;
import greendog.weixin.util.WeixinPropUtil;
/**
 * 
 * @author zhangqing
 * @date 2014-3-4 上午11:06:40
 */
public class BusService {
	/**
	 * 查询在线信息
	 * @author zhangqing
	 * @date 2014-3-4 上午11:06:33
	 * @param bus
	 * @param direction
	 * @return
	 */
	public Data getStationByBus(String bus,String direction){
		Data data = new Data();
		HttpClient client = new HttpClient();
		HostConfiguration host = new HostConfiguration();
		host.setHost(WeixinPropUtil.getInstance().getValue("url.host"),
				Integer.parseInt(WeixinPropUtil.getInstance().getValue("url.port")),
				WeixinPropUtil.getInstance().getValue("url.type"));
		client.setHostConfiguration(host);
		PostMethod method = new PostMethod(WeixinPropUtil.getInstance().getValue("url.req.querybus"));
		NameValuePair [] param= {
				new NameValuePair("direction", direction),
				new NameValuePair("lineNo", bus),
		};
		method.addParameters(param);
		try {
			client.executeMethod(method);
			String result = method.getResponseBodyAsString();
			JSONObject obj = new JSONObject(result);
			JSONObject jsonr = obj.getJSONObject("jsonr");
			List<Station> listStation = new ArrayList<Station>();
			JSONArray map = jsonr.getJSONObject("data").getJSONArray("map");
			for(int i =0;i<map.length();i++){
				JSONObject m = map.getJSONObject(i);
				Station station = new Station();
				station.setJingdu(m.getDouble("jingdu"));
				station.setOrder(m.getInt("order"));
				station.setStopId(m.getString("stopId"));
				station.setStopName(m.getString("stopName"));
				station.setStopNo(m.getString("stopNo"));
				station.setWeidu(m.getDouble("weidu"));
				listStation.add(station);
			}
			data.setListStation(listStation);
			
			List<Bus> listBus = new ArrayList<Bus>();
			Map<Integer,List<Bus>> mapBus = new HashMap<Integer, List<Bus>>();
			JSONArray bus1 = jsonr.getJSONObject("data").getJSONArray("bus");
			for(int i =0;i<bus1.length();i++){
				JSONObject b = bus1.getJSONObject(i);
				Bus bs = new Bus();
				bs.setArrived(b.getInt("arrived"));
				bs.setBusNum(b.getInt("busNum"));
				bs.setOrder(b.getInt("order"));
				bs.setStopId(b.getString("stopId"));
				listBus.add(bs);
				List<Bus> ml = mapBus.get(new Integer(bs.getOrder()));
				if(ml==null||ml.size()==0){
					ml = new ArrayList<Bus>();
				}
				ml.add(bs);
				mapBus.put(new Integer(bs.getOrder()), ml);
			}
			data.setListBus(listBus);
			data.setMapBus(mapBus);
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return data;
	}
	public String getStringByBus(String bus1,String direction){
		StringBuffer result = new StringBuffer();
		Data data = getStationByBus(bus1, direction);
		//System.out.println(data.getListStation().size());
		//System.out.println(data.getListBus().size());
		for(Station station:data.getListStation()){
			List<Bus> busList = data.getMapBus().get(new Integer(station.getOrder()));
			if(busList!=null&&busList.size()>0){
				for(Bus bus:busList){
					if(bus.getArrived()==1){
						result.append("--");
					}else{
						result.append("↓");
					}
				}
				result.append("\n");
			}
			result.append(station.getOrder()+station.getStopName()+"\n");
		}
		
		return result.toString();
	}
}

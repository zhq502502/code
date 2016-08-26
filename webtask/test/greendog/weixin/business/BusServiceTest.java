package greendog.weixin.business;

import java.util.LinkedList;
import java.util.List;

import greendog.weixin.beans.bus.Bus;
import greendog.weixin.beans.bus.Data;
import greendog.weixin.beans.bus.Station;

import org.junit.Before;
import org.junit.Test;

public class BusServiceTest {
	private BusService ser;
	private String bus = "811";
	private String direction = "0";
	@Before
	public void befor(){
		ser = new BusService();
	}
	
	@Test
	public void 查询公交车(){
		Data data = ser.getStationByBus(bus, direction);
		//System.out.println(data.getListStation().size());
		//System.out.println(data.getListBus().size());
		for(Station station:data.getListStation()){
			List<Bus> busList = data.getMapBus().get(new Integer(station.getOrder()));
			if(busList!=null&&busList.size()>0){
				for(Bus bus:busList){
					if(bus.getArrived()==1){
						System.out.print("--");
					}else{
						System.out.print("↓");
					}
				}
				System.out.println("");
			}
			System.out.println(station.getOrder()+station.getStopName());
		}
	}
}

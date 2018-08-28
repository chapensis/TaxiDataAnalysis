package com.yangchang.TaxiDataAnalysis.tools;

import java.util.List;
import java.util.Map;

import com.yangchang.TaxiDataAnalysis.bean.CoordinateBean;

public class MDP {

	/**
	 * 通过一个数字，反向返回一个坐标
	 *
	 * @throws Exception
	 */
	public static CoordinateBean getCoordinateByANumber(
			List<Map.Entry<Integer, CoordinateBean>> listCoordinate, int number) {
		Map.Entry<Integer, CoordinateBean> mapping = listCoordinate.get(number);
		CoordinateBean c = mapping.getValue();
		return c;
	}
}

//hilbert_map = { 
//'a': {(0, 0): (0, 'd'), (0, 1): (1, 'a'), (1, 0): (3, 'b'), (1, 1): (2, 'a')}, 
//'b': {(0, 0): (2, 'b'), (0, 1): (1, 'b'), (1, 0): (3, 'a'), (1, 1): (0, 'c')}, 
//'c': {(0, 0): (2, 'c'), (0, 1): (3, 'd'), (1, 0): (1, 'c'), (1, 1): (0, 'b')}, 
//'d': {(0, 0): (0, 'a'), (0, 1): (3, 'c'), (1, 0): (1, 'd'), (1, 1): (2, 'd')}}
package com.yangchang.TaxiDataAnalysis.tools;

import java.util.*;

import com.yangchang.TaxiDataAnalysis.bean.CoordinateBean;

class Square {
	String sequence;
	String point;

	Square(String str, String str1) {
		sequence = str; // 1~4象限对应的值 0 1 2 3
		point = str1; // 1~4象限对应的子图
	}
}

public class Hilbert {

	/**
	 * 返回一个2^order为边长的升序排列的list 在希尔伯特曲线中，数字和坐标的转换
	 *
	 * @param order
	 * @return
	 */
	public static List<Map.Entry<Integer, CoordinateBean>> getMapList(int order) {
		Map<Integer, CoordinateBean> map = new HashMap<>();
		int length = (int) Math.pow(2, order);
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				int number = Hilbert
						.getIntNumberByPositionAndOrder(i, j, order);
				map.put(number, new CoordinateBean(i, j));
			}
		}
		List<Map.Entry<Integer, CoordinateBean>> list = MathUtil
				.sortMapOfCoordinateBean(map);
		return list;
	}

	public static int getIntNumberByPositionAndOrder(int x, int y, int order) {
		Square a, b, c, d;
		a = new Square("3012", "bdaa");
		b = new Square("3210", "abbc");
		c = new Square("1230", "ccdb");
		d = new Square("1032", "dacd");

		int position = 0;
		Square current = a; // 当前层级的表
		while (--order >= 0) {
			position *= 4;
			int quad_x = ((x & (1 << order)) != 0) ? 1 : 0;
			int quad_y = ((y & (1 << order)) != 0) ? 1 : 0;
			int area = -1;
			if (quad_x == 0 && quad_y == 0)
				area = 2;
			else if (quad_x == 0 && quad_y == 1)
				area = 3;
			else if (quad_x == 1 && quad_y == 0)
				area = 1;
			else if (quad_x == 1 && quad_y == 1)
				area = 4;
			// System.out.println(area + "  " + position);
			char q = '0';
			int tmp = (int) (current.sequence.toCharArray()[area - 1])
					- (int) q;
			// System.out.println("tmp" + tmp);
			position += tmp;
			char s = current.point.toCharArray()[area - 1];
			if (s == 'a')
				current = a;
			else if (s == 'b')
				current = b;
			else if (s == 'c')
				current = c;
			else
				current = d;
		}
		return position;
	}

	public static void main(String[] args) {
		while (true) {
			Scanner input = new Scanner(System.in);

			System.out.println("Input x, y, order:");
			int x = input.nextInt();
			int y = input.nextInt();
			int order = input.nextInt();

			System.out.println(getIntNumberByPositionAndOrder(x, y, order));
		}
	}
}

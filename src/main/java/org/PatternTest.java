package org;

import io.vavr.Tuple;
import io.vavr.Tuple1;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class PatternTest {
	public static void main(String[] args) {
//		List<String> names = List.of(
//				"Overstock_com_123_US_cat.gz",
//				"Overstock_com_456_British_Pro.gz",
//				"Sears_sears_pr.gz");
//		Pattern pattern = Pattern.compile("");
		test();
		test1();
	}

	public static void test() {
		HashSet<Offer> set = new HashSet<>();
		set.add(new Offer("amazon"));
		set.add(new Offer("z"));
		set.add(new Offer("nordtrom"));
		set.add(new Offer("boo"));
		set.add(new Offer("v"));
		set.add(new Offer("k"));
		set.add(new Offer("raz"));
		set.add(new Offer("mon"));
		set.add(new Offer("cu"));
		set.add(new Offer("pop"));
		set.add(new Offer("bne"));
		System.out.println(set);
	}

	public static void test1() {
		HashSet<Offer> set = new HashSet<>();
		set.add(new Offer("amazon"));
		set.add(new Offer("z"));
		set.add(new Offer("nordtrom"));
		set.add(new Offer("boo"));
		set.add(new Offer("v"));
		set.add(new Offer("k"));
		set.add(new Offer("raz"));
		set.add(new Offer("mon"));
		set.add(new Offer("cu"));
		set.add(new Offer("pop"));
		set.add(new Offer("bne"));
		System.out.println(set);
	}
	private static class Offer {
		private String merchant;

		public Offer(String merchant) {
			this.merchant = merchant;
		}

		public String getMerchant() {
			return merchant;
		}

		public void setMerchant(String merchant) {
			this.merchant = merchant;
		}

		@Override
		public String toString() {
			return "Offer{" +
					"merchant='" + merchant + '\'' +
					'}';
		}
	}
}

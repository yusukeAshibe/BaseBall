package com.example.service;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ScrapeGiantsService {

	public void scrape() {
		PrintWriter p = null;
		Document document = null;
		int num = 1;
		try {
			p = new PrintWriter(new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream("C:\\mercari\\train2.tsv"), "UTF8")));// 出力するファイルを指定
//			p.print("id");
//			p.print(",");
//			p.print("球場");
//			p.print(",");
//			p.print("チーム1");
//			p.print(",");
//			p.print("チーム2");
//			p.print(",");
//			p.print("チーム1先発");
//			p.print(",");
//			p.print("チーム1得点");
//			p.print(",");
//			p.print("チーム2得点");
//			p.print(",");
//			p.print("試合状況");
//			p.print(",");
//			p.print("チーム2先発");
//			p.print("");
//			p.println();
			// document = Jsoup.connect("https://baseball.yahoo.co.jp/npb/").get();
			// document = Jsoup.connect("https://baseball.yahoo.co.jp/npb/schedule/").get();
			// document = Jsoup.connect("https://baseball-freak.com/game/").get();
			// Jsoup.connect("https://baseball.yahoo.co.jp/npb/schedule/?gameKindIds=5").get();
			document = Jsoup.connect("https://baseball-freak.com/game/giants.html").get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Elements kekka = document.select("div.wl");// 何勝何敗その他
		Elements days = document.select("td.no-pc");// 日付
		Elements audiences = document.select("table.tschedule");

		p.print(kekka.text());

		for (Element day : days) {
			p.print(day.text().replace(" ", "\t"));
			p.println("\n");

			System.out.println(day.text().replace(" ", "\t"));
			System.out.print("");

		}
		p.close();
	}
}

package com.alekseysamoylov.crawler;


import com.alekseysamoylov.crawler.model.Person;
import com.alekseysamoylov.crawler.service.IntimByGirlPageParser;
import com.alekseysamoylov.crawler.service.IntimByLinksParser;
import com.alekseysamoylov.crawler.service.LinksParcer;
import com.alekseysamoylov.crawler.service.PageParser;

import java.util.List;
import java.util.stream.Collectors;

public class BasicWebCrawler {
    private final LinksParcer linksParcer;
    private final PageParser pageParser;

    public BasicWebCrawler(LinksParcer linksParcer, PageParser pageParser) {
        this.linksParcer = linksParcer;
        this.pageParser = pageParser;
    }

    public void getPageLinks(String url) {
        List<String> personalPages = linksParcer.getLinks(url);
        System.out.println(personalPages);
        List<Person> personList = personalPages.stream()
                .map(pageParser::getPersonalData)
                .collect(Collectors.toList());
        System.out.println(personList);
    }

    public static void main(String[] args) {
        new BasicWebCrawler(new IntimByLinksParser(), new IntimByGirlPageParser())
                .getPageLinks("http://in.timby.net/cgi-bin/select.pl?Gender=woman&Orientation=any&penpal=2&friendship=2&flirt=2&marriage=2&sponsor=2&money=2&City=0&newcity=&AgeMin=0&AgeMax=99&Social=any&Added=any&OrderBy=datepublished");
    }

}

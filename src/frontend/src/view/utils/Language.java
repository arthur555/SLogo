package view.utils;

import java.util.*;

public class Language {
    public static final int COMMANDS = 0;
    public static final int QUERIES = 1;
    public static final int MATH_OPS = 2;
    public static final int BOOLEAN_OPS = 3;
    public static final int KEYWORDS = 4;

    private static final String REF_PATH = "ref/";
    private static final String BOOLEAN_OPS_PATH = "booleanops";
    private static final String COMMANDS_PATH = "commands";
    private static final String KEYWORDS_PATH = "keywords";
    private static final String MATH_OPS_PATH = "mathops";
    private static final String QUERIES_PATH = "queries";

    private static final String NAME = "name";
    private static final String SUFFIX = "suffix";
    private static final String SEP = ",";

    public static final List<ResourceBundle> REF = Collections.unmodifiableList(
            List.of(
                    ResourceBundle.getBundle(REF_PATH+COMMANDS_PATH),
                    ResourceBundle.getBundle(REF_PATH+QUERIES_PATH),
                    ResourceBundle.getBundle(REF_PATH+MATH_OPS_PATH),
                    ResourceBundle.getBundle(REF_PATH+BOOLEAN_OPS_PATH),
                    ResourceBundle.getBundle(REF_PATH+KEYWORDS_PATH)
            )
    );

    public static Set<String> extractPrefix(int which) { return extractPrefixes(REF.get(which)); }
    public static Set<String> extractPrefixes(ResourceBundle bundle) {
        var keywords = new LinkedHashSet<String>();
        var suffixes = bundle.getString(SUFFIX).split(SEP);
        var keyIt = bundle.getKeys().asIterator();
        while(keyIt.hasNext()) {
            var key = keyIt.next();
            if(!(key.equals(SUFFIX) || key.equals(NAME))) {
                for (var suffix : suffixes) key = key.replaceAll(suffix, "");
                keywords.add(key);
            }
        } return keywords;
    }

    public static String[] suffixes(int which) { return suffixes(REF.get(which)); }
    public static String[] suffixes(ResourceBundle r) { return r.getString(SUFFIX).split(SEP); }

    public static String repName(int which) { return repName(REF.get(which)); }
    public static String repName(ResourceBundle r) { return r.getString(NAME); }
    public static Set<String> commands() { return extractPrefix(COMMANDS); }
    public static Set<String> queries() { return extractPrefix(QUERIES); }
    public static Set<String> mathops() { return extractPrefix(MATH_OPS); }
    public static Set<String> boolops() { return extractPrefix(BOOLEAN_OPS); }
    public static Set<String> keywords() { return extractPrefix(KEYWORDS); }
    public static Set<String> nonKeywords() {
        var aggregate = new LinkedHashSet<String>();
        for(int i = 0 ; i < REF.size() ; i ++) {
            if(i != KEYWORDS) aggregate.addAll(extractPrefix(i));
        } return aggregate;
    }
}

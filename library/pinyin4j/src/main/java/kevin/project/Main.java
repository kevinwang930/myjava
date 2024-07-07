package kevin.project;

import net.sourceforge.pinyin4j.PinyinHelper;

import java.util.Arrays;

public class Main {

    public static void pinyinLearn() {
        String[] str = PinyinHelper.toHanyuPinyinStringArray('中');
        System.out.println(Arrays.toString(str));
    }

    public static void main(String[] args) {
        pinyinLearn();
    }
}
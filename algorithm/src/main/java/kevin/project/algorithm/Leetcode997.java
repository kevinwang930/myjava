package kevin.project.algorithm;

import java.util.HashSet;
import java.util.Set;

/*
小镇里有 n 个人，按从 1 到 n 的顺序编号。传言称，这些人中有一个暗地里是小镇法官。

如果小镇法官真的存在，那么：

小镇法官不会信任任何人。
每个人（除了小镇法官）都信任这位小镇法官。
只有一个人同时满足属性 1 和属性 2 。
给你一个数组 trust ，其中 trust[i] = [ai, bi] 表示编号为 ai 的人信任编号为 bi 的人。

如果小镇法官存在并且可以确定他的身份，请返回该法官的编号；否则，返回 -1 。

来源：力扣（LeetCode）
链接：https://leetcode.cn/problems/find-the-town-judge
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。*/
public class Leetcode997 {
    public int findJudge(int n, int[][] trust) {
        int[] trustTimes = new int[n];
        Set<Integer> noJudges = new HashSet<>();
        for (int[] ints : trust) {
            trustTimes[ints[0]-1] = trustTimes[ints[0]-1] -1 ;
            trustTimes[ints[1]-1] = trustTimes[ints[1]-1]+1;
        }
        for (int i = 0; i < n; i++) {
            if (trustTimes[i] == n-1) {
                return i+1;
            }
        }
        return -1;
    }
}

package kevin.project.fastjson;


import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;

import java.util.*;

/**
 * @version 1.0
 * @ClassName Recommendation
 * @Description 待播放作品
 * @Date 6/19/24
 **/
@Data
public class SimpleGroupRecommendation  {
    /**
     * position in database
     */
    private long watermark;

    /**
     * artifacts wait to be played
     * map key artifactId
     * map value artifact weight
     */
    private List<Map<Long, Integer>> groupInfo;

    private List<Long> recommends;

    private List<Long> plays;

    private int total_cnt;

    private int play_cnt;

    /**
     * group添加位置
     */
    private int position;


    /**
     * 移除上次推荐内容
     */
    public void updateLast() {
        if (CollectionUtils.isEmpty(recommends)) {
            return;
        }
        for (Map<Long, Integer> group : groupInfo) {
            for (Long id : recommends) {
                group.remove(id);
            }
            Set<Long> keys = group.keySet();
            for (Long id : keys) {
                int cv = group.get(id);
                if (cv <= 1) {
                    group.remove(id);
                } else {
                    group.put(id, group.get(id) - 1);
                }
            }
        }
        play_cnt += plays.size();
        total_cnt -= recommends.size() - plays.size();
        recommends.clear();
        plays.clear();
    }

    /**
     * 待播放内容填充入推荐
     *
     * @param toPlay 带播放id
     */
    public void fill(List<Long> toPlay) {
        int size = toPlay.size();
        this.watermark = toPlay.get(size - 1);
        this.total_cnt += size;
        for (int i = 0; i < toPlay.size(); i++) {
            int groupIndex = (i + position) % groupInfo.size();
            Map<Long, Integer> group = groupInfo.get(groupIndex);
            group.put(toPlay.get(i), total_cnt);
        }
        this.position = (toPlay.size() + position) % groupInfo.size();
    }

    /**
     * 随机更新已播放内容到播放列表
     *
     * @param contents 已播放列表
     * @return  更新数量
     */

    public int fillRandom(List<Long> contents) {
        int result = 0;
        for (Long content : contents) {
            if (contains(content)) {
                continue;
            }
            int groupIndex = (result + position) % groupInfo.size();
            groupInfo.get(groupIndex).put(content, total_cnt);
            result++;
        }
        return result;
    }

    public boolean reachThreshold() {
        if (CollectionUtils.isNotEmpty(recommends)) {
            updateLast();
        }
        if ((5 * play_cnt) - (4 * total_cnt) > 0) {
            return true;
        }
        return false;
    }


    public List<Long> recommend() {

        List<Long> recommends = new ArrayList<>();
        for (Map<Long, Integer> group : groupInfo) {
            Set<Long> keySet = group.keySet();
            int random = new Random().nextInt(group.size());
            int index = 0;
            for (Long key : keySet) {
                if (index == random) {
                    recommends.add(key);
                    break;
                } else {
                    index++;
                }
            }
        }
        return this.recommends = recommends;
    }

    public boolean contains(long id) {
        for (Map<Long, Integer> group : groupInfo) {
            if (group.containsKey(id)) {
                return true;
            }
        }
        return false;
    }

    public static SimpleGroupRecommendation create(int groupCount) {
        SimpleGroupRecommendation recommendation = new SimpleGroupRecommendation();
        List<Map<Long, Integer>> groupInfo = new ArrayList<>();
        for (int i = 0; i < groupCount; i++) {
            groupInfo.add(new HashMap<>());
        }
        recommendation.groupInfo = groupInfo;
        return recommendation;
    }


    public void play(List<Long> contents) {
        this.plays = contents;
    }


    public long watermark() {
        return watermark;
    }
}


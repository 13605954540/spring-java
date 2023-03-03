package org.example.algorithm;

import com.alibaba.nacos.shaded.com.google.common.collect.Lists;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Optional;

/**
 * 权重轮训
 *
 */
public class Weight {

    public String go() {
        List<Item> list = mock();
        int total = list.stream().mapToInt(Item::getWeight).sum();
        int sum = 0;
        for(int i = 0; i < list.size(); i++) {
            sum += list.get(i).getWeight();
            list.get(i).setAddAllBefore(sum);
        }
        int rd = (int)(Math.random() * total + 1);
        Optional<Item> optional = list.stream().filter(item -> item.getAddAllBefore() >= rd).findFirst();
        return optional.map(Item::getUrl).orElseThrow(() -> new RuntimeException("权重计算错误！"));
    }

    private List<Item> mock() {
        return Lists.newArrayList(
                new Item("192.168.2.21", 2),
                new Item("192.168.2.22", 3),
                new Item("192.168.2.24", 5)
        );
    }

    public static void main(String[] args) {
        Weight weight = new Weight();
        System.err.println(weight.go());
    }

    @Data
    @Accessors(chain = true)
    public class Item {

        private String url;

        private Integer weight;

        //算出遍历时与前所有weight的总和
        private Integer addAllBefore;

        public Item(String url, Integer weight) {
            this.url = url;
            this.weight = weight;
        }
    }
}

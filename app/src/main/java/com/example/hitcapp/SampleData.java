package com.example.hitcapp;

import java.util.ArrayList;
import java.util.List;

public class SampleData {
    public static List<Product> getGoldProducts() {
        List<Product> list = new ArrayList<>();

        list.add(new Product(
                "Nhẫn vàng 24K",
                "Nhẫn",
                "Thiết kế tinh tế, sang trọng, phù hợp đeo hằng ngày.",
                5800000
        ));

        list.add(new Product(
                "Dây chuyền vàng Ý",
                "Dây chuyền",
                "Kiểu dáng cao cấp, nổi bật vẻ đẹp quý phái.",
                12800000

        ));

        list.add(new Product(
                "Lắc tay vàng nữ",
                "Lắc tay",
                "Mẫu lắc tay thanh lịch, nhẹ nhàng và sang trọng.",
                7600000
        ));

        list.add(new Product(
                "Bông tai vàng cao cấp",
                "Bông tai",
                "Tôn lên vẻ đẹp thanh thoát và nữ tính.",
                4200000
        ));

        list.add(new Product(
                "Vòng cổ vàng đính đá",
                "Vòng cổ",
                "Thiết kế nổi bật, phù hợp tiệc và sự kiện.",
                15600000
        ));

        list.add(new Product(
                "Nhẫn cưới vàng sang trọng",
                "Nhẫn cưới",
                "Biểu tượng bền vững của tình yêu và đẳng cấp.",
                9200000
        ));

        return list;
    }
}
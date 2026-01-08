package cn.navg.laboratory.pojo;

import lombok.*;

//分页信息
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Page {
    private Integer currentPage;
    private Integer pageSize;
    private Integer total;
}

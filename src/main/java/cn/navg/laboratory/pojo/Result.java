package cn.navg.laboratory.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//统一响应结果基类
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    private String msg;//响应标识，success/error
    private String code;//响应码，1/0
    private T data;//响应数据
    private Page page;//分页信息

    //成功 无数据
    public static <Void> Result<Void> success() {
        Result<Void> result = new Result<Void>();
        result.setMsg("success");
        result.setCode("200");
        return result;
    }

    //成功 数据
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<T>();
        result.setMsg("success");
        result.setCode("200");
        result.setData(data);
        return result;
    }

    //成功 数据+分页
    public static <T> Result<T> success(T data, Page page) {
        Result<T> result = new Result<T>();
        result.setMsg("success");
        result.setCode("200");
        result.setData(data);
        result.setPage(page);
        return result;
    }

    //错误响应
    public static <T> Result<T> error(T data) {
        Result<T> result = new Result<T>();
        result.setMsg("error");
        result.setCode("-1");
        result.setData(data);
        return result;
    }
}

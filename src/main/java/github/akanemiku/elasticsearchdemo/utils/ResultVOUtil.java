package github.akanemiku.elasticsearchdemo.utils;


public class ResultVOUtil {
    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setData(object);
        resultVO.setCode(200);
        resultVO.setMsg("SUCCESS");
        return resultVO;
    }

    public static ResultVO success() {
        return success(null);
    }

}

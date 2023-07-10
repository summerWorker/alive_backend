package com.alive_backend.controller.health_data;

import com.alive_backend.entity.health_data.Weight;
import com.alive_backend.service.health_data.WeightService;
import com.alive_backend.utils.constant.Constant;
import com.alive_backend.utils.constant.UserConstant;
import com.alive_backend.utils.msg.Msg;
import com.alive_backend.utils.msg.MsgUtil;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Id;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

@RestController
public class WeightController {
    @Autowired
    private WeightService weightService;

    @PostMapping("/weight")
    @Cacheable(value = "weightCache", key = "#data.get('user_id')+ '_' + #data.get('start_date') + '_' +#data.get('end_date')")
    public Msg getWeight(@RequestBody Map<String, Object> data) {
        /* 检验参数合法性 */
        Object id_ = data.get(UserConstant.USER_ID);
        Object date1_ = data.get(Constant.START_DATE);
        Object date2_ = data.get(Constant.END_DATE);
        if (id_ == null || date1_ == null || date2_ == null) {
            return MsgUtil.makeMsg(MsgUtil.ARG_ERROR, "传参错误{user_id:1, start_date:2023-04-01, end_date:2023-06-06}", null);
        }

        int id;
        Date date1, date2;
        try {
            id = (int) id_;
            date1 = Date.valueOf((String) date1_);
            date2 = Date.valueOf((String) date2_);
        } catch (Exception e) {
            return MsgUtil.makeMsg(MsgUtil.ARG_ERROR, e.toString(), null);
        }
        List<Weight> weight = weightService.getWeight(id, date1, date2);
        JSONArray jsonArray = JSONArray.fromObject(weight);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("weight", jsonArray);
        return MsgUtil.makeMsg(MsgUtil.SUCCESS, MsgUtil.SUCCESS_MSG, jsonObject);
    }


    @PostMapping("/add_weight")
    @CacheEvict(value = "weightCache", allEntries = true)
    public Msg AddWeight(@RequestBody Map<String,Object> data) {
        /* 检验参数合法性 */
        Object id_ = data.get(UserConstant.USER_ID);
        Object date_ = data.get(Constant.DATE);
        Object weight_ = data.get(Constant.WEIGHT);
        if (id_ == null || date_ == null || weight_ == null) {
            return MsgUtil.makeMsg(MsgUtil.ARG_ERROR, "传参错误{user_id:1, date:2023-04-01, weight:60}", null);
        }
        int id,year; Date date; double weight;
        try {
            id = (int) id_;
            date = Date.valueOf((String) date_);
            weight = ((Integer) weight_).doubleValue();
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            year = c.get(Calendar.YEAR);

        } catch (Exception e) {
            return MsgUtil.makeMsg(MsgUtil.ARG_ERROR, "参数格式错误："+e.toString(), null);
        }
        Weight weight1 = weightService.getWeightByYear(id, year);
        Weight weight2;
        if(weight1 == null) { // 该用户该年度没有体重记录
            /* 基本信息 */
            weight1 = new Weight();
            weight1.setUserId(id);
            weight1.setYearId(year);
            /* 体重信息 */
            JSONObject item = new JSONObject();
            item.put("date", date.toString());
            item.put("weight", weight);
            JSONArray jsonArray = new JSONArray();
            jsonArray.add(item);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("items", jsonArray);
            weight1.setDetailValue(jsonObject.toString());
            weight2 = weightService.addWeight(weight1);
        } else {
            if(weight1.getDetailValue().equals("")) {
                weight1.setDetailValue("{\"items\":[]}");
            }
            JSONObject jsonObject = JSONObject.fromObject(weight1.getDetailValue());
            JSONArray jsonArray = jsonObject.getJSONArray("items");
            /* 检查当天是否有体重记录 */
            boolean flag = false;
            for(int i = 0; i < jsonArray.size(); i++) {
                JSONObject item = jsonArray.getJSONObject(i);
                if(item.getString("date").equals(date.toString())) {
                    /* 有记录则覆盖 */
                    item.put("value", weight);
                    jsonArray.set(i, item);
                    flag = true;
                    break;
                }
            }
            /* 没有记录则添加 */
            if(!flag) {
                JSONObject item = new JSONObject();
                item.put("date", date.toString());
                item.put("value", weight);
                jsonArray.add(item);
            }
            jsonObject.put("items", jsonArray);
            weight1.setDetailValue(jsonObject.toString());
            weight2 = weightService.addWeight(weight1);
        }
        return MsgUtil.makeMsg(MsgUtil.SUCCESS, MsgUtil.SUCCESS_MSG, JSONObject.fromObject(weight2));

    }


}

package com.demo.web.controllers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.demo.web.models.TagsModel;

@Controller
@RequestMapping(value = "/tags")
public class TagsController {
    
    @RequestMapping(value="/test", method = {RequestMethod.GET})
    public String test(Model model){

        if(!model.containsAttribute("contentModel")){        
            
            TagsModel tagsModel=new TagsModel();
            
            tagsModel.setUsername("aaa");
            tagsModel.setPassword("bbb");
            tagsModel.setTestBoolean(true);
            tagsModel.setSelectArray(new String[] {"arrayItem 路人甲"});
            tagsModel.setTestArray(new String[] {"arrayItem 路人甲","arrayItem 路人乙","arrayItem 路人丙"});
            tagsModel.setRadiobuttonId(1);
            tagsModel.setSelectId(2);
            tagsModel.setSelectIds(Arrays.asList(1,2));
            Map<Integer,String> map=new HashMap<Integer,String>();
            map.put(1, "mapItem 路人甲");
            map.put(2, "mapItem 路人乙");
            map.put(3, "mapItem 路人丙");
            tagsModel.setTestMap(map);
            tagsModel.setRemark("备注...");
            
            model.addAttribute("contentModel", tagsModel);
        }
        return "tagstest";
    }
    
}
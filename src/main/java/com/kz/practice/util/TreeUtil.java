package com.kz.practice.util;

import com.kz.practice.entity.Tree;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Description 树结构工具
 * @Author KatieZ
 * @Date Created in 10:10  10:10
 */
@Slf4j
public class TreeUtil {

    public static void main(String[] args){
        List<Tree> list = new ArrayList<>();
        IntStream.range(1,3).forEach(i -> {
            Tree campus = new Tree();
            campus.setId((long) i);
            campus.setName(i == 1?"松江校区":"延安路校区");
            campus.setLevel(1);
            campus.setPid(0L);
            list.add(campus);
        });
        IntStream.range(3,5).forEach(i -> {
            Tree building = new Tree();
            building.setId((long) i);
            building.setName(i == 3?"材料大楼":"耗材大楼");
            building.setLevel(2);
            building.setPid(1L);
            list.add(building);
        });
        IntStream.range(5,15).forEach( i -> {
            Tree floor = new Tree();
            floor.setId((long) i);
            floor.setName(i+"");
            floor.setLevel(3);
            floor.setPid(i >= 5 && i < 10 ? 3L: 4L);
            list.add(floor);
        });

        IntStream.range(15,35).forEach( i -> {
            Tree room = new Tree();
            room.setId((long) i);
            room.setName(i+"");
            room.setLevel(4);
            if(i >= 15 && i<=20){
                room.setPid(5L);
            }
            if(i >= 21 && i<=25){
                room.setPid(6L);
            }
            if(i >= 26 && i<=30){
                room.setPid(7L);
            }
            if(i >= 31 && i<=35){
                room.setPid(8L);
            }
            list.add(room);
        });

        log.info(Arrays.toString(list.toArray()));

        Optional<Tree> campusOptional = list.stream().filter(t -> t.getName().equals("松江校区")).findFirst();
        if(campusOptional.isPresent()){
            Optional<Tree> buildingOptional = list.stream().filter(t -> t.getName().equals("材料大楼") && t.getPid().equals(campusOptional.get().getId())).findFirst();
            if(buildingOptional.isPresent()){
                Optional<Tree> floorOptional = list.stream().filter(t -> t.getName().equals("5") && t.getPid().equals(buildingOptional.get().getId())).findFirst();
                if(floorOptional.isPresent()){
                    Optional<Tree> roomOptional = list.stream().filter(t -> t.getName().equals("15") && t.getPid().equals(floorOptional.get().getId())).findFirst();
                    if(roomOptional.isPresent()){
                       log.info(roomOptional.get().getId()+"");
                    }
                }
            }
        }
    }

    public void getChild(List<Tree> list){

    }

}

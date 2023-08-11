package com.study.compicafe.menu.controller;

import com.study.compicafe.menu.entity.Menu;
import com.study.compicafe.menu.service.MenuService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/menu")
public class ManuController {
    private final MenuService menuService;
    public ManuController(MenuService menuService){
        this.menuService=menuService;
    }



    @GetMapping("{menuCode}")
    public ResponseEntity<Object>findMenyByCode(@PathVariable int menuCode){
        Menu menu = menuService.findMenuByCode(menuCode);
        if(Objects.isNull(menu)){
            return ResponseEntity.status(404).body(new String("다시입력하시오"));
        }
        return ResponseEntity.ok().body(menu);

    }


    @GetMapping("/list")
    public ResponseEntity<List<?>>findAllMenu(){
        List<Menu>menuList = menuService.findAllMenu();
        if(menuList.size() <= 0){
            List<String>error = new ArrayList<>();
            error.add("String");
            return ResponseEntity.status(404).body(error);
        }
        return ResponseEntity.ok().body(menuList);
    }


    @PostMapping("/regist")
    public ResponseEntity<?> regist(Menu menu){
        System.out.println("cnt"+ menu);
        int result = menuService.registName(menu);
        return ResponseEntity.ok().body("Success");
    }


    @PutMapping("/update")
    public ResponseEntity<?> update(Menu menu/*원래DTO로 받아와야함*/){
        Menu findMenu = menuService.findMenuByCode(menu.getMenuCode());
        if(Objects.isNull(findMenu)){
            return ResponseEntity.ok().body("데이터가 존재하지 않습니다");
        }
        System.out.println("데이터 존재 확인 완료");
        /*스냅샷기준으로 변경전[0,0,0,0] -> 변경후 [0,0,0,1].save(id)메소드호출 두개값 비교
        * --->영속성 컨텍스트 [0,0,0,1] -> DB에 반영됨
        * 영속성 컨텍스트에 없는 경우, 영속화를 진행후.save()호출시 DB에 반영됨--> 그래서 있는지 없는지 확인하는 findMenu 가 필요* */
        int result = menuService.updateMenu(findMenu, menu);
        System.out.println("서비스 호출 완료...  " + result);

        if(result > 0){
            System.out.println("수정완료 : " + result);
            return ResponseEntity.ok().body("수정완료");
        }else {
            System.out.println("수정실패 : "  + result);
            return ResponseEntity.status(400).body("수정실패");
        }
    }

    @DeleteMapping("/{delete}")
    public ResponseEntity<?> delete(@PathVariable int delete) {
        menuService.deleteCode(delete);
        return ResponseEntity.ok().body("삭제완료");
    }

}

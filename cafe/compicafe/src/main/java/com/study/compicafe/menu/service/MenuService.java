package com.study.compicafe.menu.service;

import com.study.compicafe.menu.entity.Menu;
import com.study.compicafe.menu.repository.MenuRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class MenuService {
    private final MenuRepository menuRepository;

    public MenuService(MenuRepository menuRepository){
        this.menuRepository=menuRepository;
    }
    public Menu findMenuByCode(int menuCode){
        Menu menu = menuRepository.findById(menuCode);
        return menu;
    }

    public List<Menu> findAllMenu(){
        List<Menu> menuList=menuRepository.findAll();
        return menuList;
    }

    @Transactional
    public int registName(Menu menu){
        Menu result = menuRepository.save(menu);
        System.out.println(result);

        if (Objects.isNull(result)){
            return 0;
        }else {
            return 1;
        }
    }
    @Transactional
    public int updateMenu(Menu findMenu, Menu updaMenu) {

        System.out.println("안녕 난 서비스야");
        //만약 Objects의 값이 널이 아닐 경우 updaMenu의 메뉴 네임을 가져오고 그 안에 수식들을 실행한다.
        if(!Objects.isNull(updaMenu.getMenuName())){

            findMenu.setMenuName(updaMenu.getMenuName());
            System.out.println("menu -> : "+ findMenu.getMenuName());
            findMenu.setMenuPrice(updaMenu.getMenuPrice());
            System.out.println("price => : " + findMenu.getMenuPrice());
            findMenu.setCategoryCode(updaMenu.getCategoryCode());
            System.out.println("category => : " + findMenu.getCategoryCode());

        }
        Menu result = menuRepository.save(findMenu);
        System.out.println("과연 " + result);
        if(Objects.isNull(result)){
            return 0;
        }else {
            return 1;
        }
    }
    @Transactional
    public void deleteCode(int del){
       //  menuRepository.delete(/*이렇게 하면 쿼리 한번 더 타야됨*/);
        menuRepository.deleteById(del);
        Menu menu = menuRepository.findById(del); //존재하는지 먼저 찾기->영속화에서 제거->null값
        System.out.println(menu);


    }
}

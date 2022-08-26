package com.example.demo;

import Node.Node;
import com.sun.tools.jconsole.JConsoleContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;
import java.util.Random;

@Controller
public class canvasController {
    int circleSize = 15;

    public customBinarySearchTree sessionTree = new customBinarySearchTree(new Board(1000,1000), circleSize);

    public AVLTree sessionAVLTree = new AVLTree(new Board(1000,1000), circleSize);
    @RequestMapping(value="AVLTree", method = RequestMethod.GET)
    private String getAVLTree(
            Model model,
            @RequestParam(value = "insertValue", required = false) String insertValue,
            @RequestParam(value = "removeValue", required = false) String removeValue,
            @RequestParam(value = "insertMultiple", required = false) String insertMultiple,
            @RequestParam(value = "randomCount", required = false) String randomCount
    ){

        try {
            if (insertValue != null && insertValue != "") {
                sessionAVLTree.insert(Integer.parseInt(insertValue));
            }
            if (removeValue != null && removeValue != "") {
                sessionAVLTree.remove(Integer.parseInt(removeValue));
            }
            if (insertMultiple != null && insertMultiple != "") {
                insertMultiple = insertMultiple.replaceAll(" ", "");
                String[] insertValues = insertMultiple.split(",");
                for (var value : insertValues) {
                    sessionAVLTree.insert(Integer.parseInt(value));
                }
            }
        }catch (Exception e){
            System.out.printf("Can't insert string");
        }

        if (randomCount != null && randomCount != ""){
            int toIntCount = Integer.parseInt(randomCount);
            if(toIntCount != 0){
                for (int i = 0; i < toIntCount; i++) {
                    Random rn = new Random();
                    sessionAVLTree.insert(rn.nextInt(10000));
                }
            }
        }

        int count = sessionAVLTree.getCount(sessionAVLTree.root);
        Board board = new Board(count*50 + 1000,count*50 + 1000);
        List<Node> treeAsList = sessionAVLTree.toList();
        model.addAttribute("info", "conming from controller");
        model.addAttribute("king", new Person("Leighton", 19));
        model.addAttribute("xCoord",10);
        model.addAttribute("yCoord",10);
        model.addAttribute("size",circleSize);
        model.addAttribute("board",board);
        model.addAttribute("binarySearchTree", sessionAVLTree);
        model.addAttribute("treeAsList",treeAsList);

        return "canvas";
    }


    @RequestMapping(value="bstree", method = RequestMethod.GET)
    private String getBinarySearchTree(
            Model model,
            @RequestParam(value = "insertValue", required = false) String insertValue,
            @RequestParam(value = "removeValue", required = false) String removeValue,
            @RequestParam(value = "insertMultiple", required = false) String insertMultiple,
            @RequestParam(value = "randomCount", required = false) String randomCount
    ){

        try {
            if (insertValue != null && insertValue != "") {
                sessionTree.insert(Integer.parseInt(insertValue));
            }
            if (removeValue != null && removeValue != "") {
                sessionTree.remove(Integer.parseInt(removeValue));
            }
            if (insertMultiple != null && insertMultiple != "") {
                insertMultiple = insertMultiple.replaceAll(" ", "");
                String[] insertValues = insertMultiple.split(",");
                for (var value : insertValues) {
                    sessionTree.insert(Integer.parseInt(value));
                }
            }
        }catch (Exception e){
            System.out.printf("Can't insert string");
        }

        if (randomCount != null && randomCount != ""){
            int toIntCount = Integer.parseInt(randomCount);
            if(toIntCount != 0){
                for (int i = 0; i < toIntCount; i++) {
                    Random rn = new Random();
                    sessionTree.insert(rn.nextInt(10000));
                }
            }
        }

        int count = sessionTree.getCount(sessionTree.root);
        Board board = new Board(count*50 + 1000,count*50 + 1000);
        List<Node> treeAsList = sessionTree.toList();
        model.addAttribute("info", "conming from controller");
        model.addAttribute("king", new Person("Leighton", 19));
        model.addAttribute("xCoord",10);
        model.addAttribute("yCoord",10);
        model.addAttribute("size",circleSize);
        model.addAttribute("board",board);
        model.addAttribute("binarySearchTree", sessionTree);
        model.addAttribute("treeAsList",treeAsList);
        return "canvas";
    }

    @GetMapping
    public String getCanvas(
            Model model,
            @RequestParam(value = "insertValue", required = false) String insertValue,
            @RequestParam(value = "removeValue", required = false) String removeValue,
            @RequestParam(value = "insertMultiple", required = false) String insertMultiple,
            @RequestParam(value = "randomCount", required = false) String randomCount
            )
    {

//        getBinarySearchTree(model,insertValue,removeValue,insertMultiple,randomCount);

     return "canvas";
    }
}

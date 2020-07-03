package com.atguigu.car.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreeNodeBuilder {

    public static List<TreeNode> getTreeNode(List<TreeNode> treeNodes, Integer id) {

        // 将得到的TreeNode集合进行遍历，将子集合放到父集合中
        Map<Integer, TreeNode> nodes = new HashMap<>();
        // 如果pid为1，说明是父节点
        for (TreeNode treeNode : treeNodes) {
            if (treeNode.getPid() == id) {
                nodes.put(treeNode.getId(), treeNode);
            }
        }
        // 将子节点的集合放到父节点中
        for (TreeNode treeNode : treeNodes) {
            TreeNode parentNode = nodes.get(treeNode.getPid());
            if (parentNode != null && parentNode.getId() != 0) {
                parentNode.getChildren().add(treeNode);
            }
        }
        return new ArrayList<>(nodes.values());

    }
}

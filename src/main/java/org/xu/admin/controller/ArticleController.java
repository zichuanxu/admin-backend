package org.xu.admin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.xu.admin.annotation.Auth;
import org.xu.admin.common.Result;
import org.xu.admin.entity.Article;
import org.xu.admin.service.IArticleService;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private IArticleService articleService;

    // 分页查询
    @GetMapping("/page")
    @Auth
    public Result<Page<Article>> findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        Page<Article> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id"); // 默认按ID倒序
        Page<Article> result = articleService.page(page, queryWrapper);
        return Result.success(result);
    }

    // 新增
    @PostMapping("/add")
    @Auth
    public Result<Boolean> add(@RequestBody Article article) {
        return Result.success(articleService.save(article));
    }

    // 更新
    @PutMapping("/update")
    @Auth
    public Result<Boolean> update(@RequestBody Article article) {
        return Result.success(articleService.updateById(article));
    }

    // 单个删除
    @DeleteMapping("/{id}")
    @Auth
    public Result<Boolean> delete(@PathVariable Integer id) {
        return Result.success(articleService.removeById(id));
    }

    // 批量删除
    @PostMapping("/delete/batch")
    @Auth
    public Result<Boolean> deleteBatch(@RequestBody List<Integer> ids) {
        return Result.success(articleService.removeByIds(ids));
    }

    @GetMapping("/{id}")
    @Auth
    public Result<Article> findById(@PathVariable Integer id) {
        Article article = articleService.getById(id);
        return Result.success(article);
    }
}

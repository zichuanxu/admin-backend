package org.xu.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.xu.admin.entity.Article;

@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
    // MyBatis-Plus 已内置基础CRUD，无需手写SQL
}
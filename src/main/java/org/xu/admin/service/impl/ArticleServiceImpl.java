package org.xu.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.xu.admin.entity.Article;
import org.xu.admin.mapper.ArticleMapper;
import org.xu.admin.service.IArticleService;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {
    // 继承 ServiceImpl 即可直接使用 save, removeById, page 等方法
}

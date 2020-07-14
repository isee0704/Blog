package com.shyoc.service.impl;

import com.shyoc.dao.LinkDao;
import com.shyoc.pojo.Link;
import com.shyoc.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;


@Service
public class LinkServiceImpl implements LinkService {
    @Autowired
    private LinkDao linkDao;

    @Override
    public List<Link> findLinkList() {
        return linkDao.findLinkList();
    }

    @Override
    public List<Link> findAllowedLinkList() {
        return linkDao.findAllowedLinkList();
    }

    @Override
    public Integer saveLink(Link link) {
        if (!validate(link)) {
            throw new NullPointerException("传入参数非空校验不通过");
        }
        link.setCreateTime(System.currentTimeMillis());
        Integer saveLink = linkDao.saveLink(link);
        if (saveLink < 1) {
            throw new RuntimeException("友情链接上传失败");
        }
        return saveLink;
    }

    @Override
    public Integer updateLink(Link link) {
        Integer updateLink = linkDao.updateLink(link);
        if (updateLink < 1) {
            throw new RuntimeException("友情链接上传失败");
        }
        return updateLink;
    }

    @Override
    public void deleteLink(Long id) {
        linkDao.deleteLink(id);
    }

    public Boolean validate(Link link) {
        return !StringUtils.isEmpty(link.getUrl()) &&
                !StringUtils.isEmpty(link.getName()) &&
                !StringUtils.isEmpty(link.getPicture());
    }
}

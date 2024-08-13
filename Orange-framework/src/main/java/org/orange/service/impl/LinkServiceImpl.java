package org.orange.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.orange.constans.SystemConstants;
import org.orange.domain.dto.LinkDto;
import org.orange.domain.entity.Link;
import org.orange.domain.response.ResponseResult;
import org.orange.domain.vo.LinkVo;
import org.orange.domain.vo.PageVo;
import org.orange.mapper.LinkMapper;
import org.orange.service.LinkService;
import org.orange.utils.BeanCopyUtils;
import org.orange.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * @BelongsProject: Orange_Blog
 * @ClassName LinkServiceImpl
 * @Description TODO
 * @Author WangZJ0908
 * @Date 2024/8/5
 * @Version: 1.0
 */
@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {

    @Autowired
    private LinkMapper linkMapper;
    @Override
    public ResponseResult getAllLink() {
        LambdaQueryWrapper<Link> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(Link::getStatus, SystemConstants.LINK_STATUS_PASS);
        List<Link> linkList=linkMapper.selectList(queryWrapper);
        List<LinkVo> linkVos = BeanCopyUtils.copyBeanList(linkList, LinkVo.class);
        return ResponseResult.okResult(linkVos);
    }

    @Override
    public ResponseResult getAllLinks(Integer pageNum, Integer pageSize, String name, String status) {
        LambdaQueryWrapper<Link> queryWrapper=new LambdaQueryWrapper<>();
        if(name!=null){
            queryWrapper.like(Link::getName,name);
        }
        if(status!=null&&!"".equals(status)){
            queryWrapper.eq(Link::getStatus,status);
        }
        queryWrapper.eq(Link::getDelFlag, SystemConstants.NOR);
        List<Link> links=linkMapper.selectList(queryWrapper);
        Page<Link> page=new Page<>(pageNum,pageSize);
        page.setRecords(links);
        page.setTotal(links.size());
        PageVo pageVo=new PageVo(page.getRecords(),page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult addLink(LinkDto linkDto) {
        Link link = BeanCopyUtils.copyBean(linkDto, Link.class);
        link.setCreateBy(SecurityUtils.getUserId());
        link.setCreateTime(new Date());
        linkMapper.insert(link);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getLink(Long id) {
        Link link = linkMapper.selectById(id);
        return ResponseResult.okResult(link);
    }

    @Override
    public ResponseResult updateLink(LinkDto linkDto) {
        Link link=BeanCopyUtils.copyBean(linkDto,Link.class);
        link.setUpdateBy(SecurityUtils.getUserId());
        link.setUpdateTime(new Date());
        linkMapper.updateById(link);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult deleteLink(List<Long> id) {
        for (Long aLong : id) {
            Link link = linkMapper.selectById(aLong);
            link.setDelFlag(SystemConstants.DELETE);
            linkMapper.updateById(link);
        }
        return ResponseResult.okResult();
    }
}

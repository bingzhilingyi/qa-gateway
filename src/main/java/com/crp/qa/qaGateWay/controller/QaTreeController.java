/**
 * huangyue
 * 2018年5月31日
 */
package com.crp.qa.qaGateWay.controller;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crp.qa.qaGateWay.domain.dto.QaTreeSimpleDto;
import com.crp.qa.qaGateWay.service.inte.QaTreeService;
import com.crp.qa.qaGateWay.util.exception.QaTreeException;
import com.crp.qa.qaGateWay.util.transfer.QaBaseTransfer;
import com.crp.qa.qaGateWay.util.transfer.QaGenericPagedTransfer;

/**
 * 层级树
 * @author huangyue
 * @date 2018年5月31日 下午8:58:07
 * @ClassName QaTreeController
 */
@RestController
@RequestMapping(path="/tree")
public class QaTreeController extends QaBaseController{

	@Resource(name="qaTreeService")
	private QaTreeService qaTreeService;
	
	/**
	 * 查找所有root节点
	 * @author huangyue
	 * @date 2018年6月1日 上午9:12:57
	 * @return
	 */
	@GetMapping(path="/getRoot")
	public QaBaseTransfer findRoot() {
		//创建返回对象
		QaBaseTransfer dto = new QaBaseTransfer();
		try {
			dto = qaTreeService.findRoot();
		} catch (QaTreeException e) {
			returnError(e, dto);
		}
		return dto;
	}
	
	/**
	 * 根据父id查找子集
	 * @author huangyue
	 * @date 2018年6月1日 上午9:15:30
	 * @param parentId
	 * @return
	 */
	@GetMapping(path="/getByParentId/{parentId}")
	public QaBaseTransfer findByParentId(@PathVariable(value="parentId") Integer parentId) {
		//创建返回对象
		QaBaseTransfer dto = new QaBaseTransfer();
		try {
			dto = qaTreeService.findByParentId(parentId);
		} catch (QaTreeException e) {
			returnError(e, dto);
		}
		return dto;
	}
	
	/**
	 * 通过id查找节点
	 * @author huangyue
	 * @date 2018年6月1日 上午9:22:12
	 * @param id
	 * @return
	 */
	@GetMapping(path="/getById/{Id}")
	public QaBaseTransfer findById(@PathVariable(value="Id") Integer id) {
		//创建返回对象
		QaBaseTransfer dto = new QaBaseTransfer();
		try {
			dto = qaTreeService.findById(id);
		} catch (QaTreeException e) {
			returnError(e, dto);
		}
		return dto;
	}
	
	/**
	 * 根据title精确查找
	 * @author huangyue
	 * @date 2018年5月31日 下午2:40:14
	 * @param token
	 * @param title
	 * @return
	 */
	@GetMapping(path="/getByTitle")
	public QaBaseTransfer findByTitle(@RequestParam(value="title") String title) {
		//创建返回对象
		QaBaseTransfer dto = new QaBaseTransfer();
		try {
			dto = qaTreeService.findByTitle(title);
		} catch (QaTreeException e) {
			returnError(e, dto);
		}
		return dto;
	}
	
	/**
	 * 通过标题模糊查询
	 * @author huangyue
	 * @date 2018年5月31日 上午11:29:54
	 * @param token
	 * @param title
	 * @param page
	 * @param size
	 * @return
	 */
	@GetMapping(path="/getPagedByTitleLike")
	public QaGenericPagedTransfer<List<QaTreeSimpleDto>> findByTitleLike(
			@RequestParam(value="title") String title,
			@RequestParam(value="page") Integer page,
			@RequestParam(value="size") Integer size) {
		//创建返回对象
		QaGenericPagedTransfer<List<QaTreeSimpleDto>> dto = new QaGenericPagedTransfer<List<QaTreeSimpleDto>>();
		try {
			dto = qaTreeService.findPagedByTitleLike(title, page, size);
		} catch (QaTreeException e) {
			returnError(e, dto);
		}
		return dto;
	}
	
	/**
	 * 保存节点信息
	 * @author huangyue
	 * @date 2018年5月28日 下午2:06:34
	 * @param value
	 * @param node
	 * @return
	 */
	@PostMapping(path="/save")
	public QaBaseTransfer save(@RequestParam(value="node") String node){
		//创建返回对象
		QaBaseTransfer dto = new QaBaseTransfer();
		try {
			dto = qaTreeService.save(node);
		} catch (QaTreeException e) {
			returnError(e, dto);
		}
		return dto;
	}
	
	/**
	 * 更新节点信息
	 * @author huangyue
	 * @date 2018年5月29日 上午10:17:38
	 * @param token
	 * @param node
	 * @return
	 */
	@PutMapping(path="/update")
	public QaBaseTransfer update(@RequestParam(value="node") String node){
		//创建返回对象
		QaBaseTransfer dto = new QaBaseTransfer();
		try {
			dto = qaTreeService.update(node);
		} catch (QaTreeException e) {
			returnError(e, dto);
		}
		return dto;
	}
	
	/**
	 * 删除节点信息
	 * @author huangyue
	 * @date 2018年5月29日 上午10:17:45
	 * @param token
	 * @param id
	 * @return
	 */
	@DeleteMapping(path="/delete")
	public QaBaseTransfer delete(@RequestParam(value="id") Integer id){
		//创建返回对象
		QaBaseTransfer dto = new QaBaseTransfer();
		try {
			dto = qaTreeService.delete(id);
		} catch (QaTreeException e) {
			returnError(e, dto);
		}
		return dto;
	}
}
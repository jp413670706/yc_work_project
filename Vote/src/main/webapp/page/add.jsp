<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>发布新投票</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script type="text/javascript">
var isIE = !!document.all;
function AddOption()
{
	var voteoptions = document.getElementById("voteoptions");
	var _p = document.createElement("p");
	var _input = document.createElement("input");
	_input.type = "text";
	_input.className = "input-text";
	_input.setAttribute("name", "options");
	_p.appendChild(_input);
	var _a = document.createElement("a");
	_a.className = "del";
	_a.setAttribute("href", "javascript:;");
	if(isIE) {
		_a.attachEvent("onclick", DelOption);
	} else {
		_a.addEventListener("click", DelOption, false);
	}
	_a.appendChild(document.createTextNode("删除"));
	_p.appendChild(_a);
	voteoptions.appendChild(_p);
}
function DelOption(e)
{
	if(!e) e = window.event;
	var a = e.srcElement || e.target;
	var obj = a.parentNode;
	obj.parentNode.removeChild(obj);
}
</script>
</head>
<body>
<jsp:include page="top.jsp" />
<div id="voteManage" class="box">
	<h2>添加新投票</h2>
	<div class="content">
	<form method="post" action="Subject!save.action">
			<dl>
				<dt>投票内容：</dt>
				<dd>
				   <input type="hidden" name="entityId" value="<s:property value='subject.id'/>"/>
				   <input type="text" class="input-text" name="subject.title"  value="<s:property value='subject.title'/>"/>
				</dd>
				<dt>投票类型：</dt>
				<dd>
		  		   <input type="radio" name="subject.type" <s:if test="subject.type==1"> checked="checked" </s:if>value="1" />单选
				   <input type="radio" name="subject.type" <s:if test="subject.type==2"> checked="checked" </s:if>value="2" />多选
				</dd>
				<dt>投票选项：</dt>
				<s:if test="subject.id==null">
				<dd id="voteoptions">
					<p><input type="text" class="input-text" name="options" /></p>
					<p><input type="text" class="input-text" name="options" /></p>
				</dd>
				</s:if>
				<s:else>
				<dd id="voteoptions">
					<s:iterator value="subject.options" status="status">
						<p><input type="text" class="input-text" name="option_<s:property value='id'/>" value="<s:property value='name'/>"/><s:if test="#status.index>1"><a class="del" href="javascript:;" onclick="DelOption()">删除</a></s:if></p>
					</s:iterator>
				</dd>
				</s:else>
				<dt></dt>
				<dd class="button">
					<input type="image" src="images/button_submit.gif" />
					<a href="javascript:;" onclick="AddOption()">增加选项</a>
					<a href="Subject!list.action">取消操作</a>
				</dd>
			</dl>
		</form>
	</div>
</div>
<div id="footer" class="wrap">
	源辰信息 &copy; 版权所有
</div>
</body>
</html>


<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<meta charset="utf-8">
<title>第一个 ECharts 实例</title>
<!-- 引入 echarts.js -->
<script src="https://cdn.staticfile.org/echarts/4.3.0/echarts.min.js"></script>
</head>
<body>
	<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
	<div id="main" style="width: 600px; height: 400px;"></div>
	<script type="text/javascript">
		// 基于准备好的dom，初始化echarts实例
		var myChart = echarts.init(document.getElementById('main'));

		// 指定图表的配置项和数据
		var option = {
			/*
			title: {
			text: '第一个 ECharts 实例'
			},
			tooltip: {},
			legend: {
			data:['销量']
			},
			xAxis: {
			data: ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
			},
			yAxis: {},
			series: [{
			name: '销量',
			type: 'bar',
			data: [5, 20, 36, 10, 10, 120]
			}]
			 */

			//设置颜色
			/*
			xAxis: {
			    type: 'category',
			    data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
			},
			yAxis: {
			    type: 'value'
			},
			series: [{
			    data: [120, {
			        value: 200,
			        itemStyle: {
			            color: '#33ccff'
			        }
			    }, 150, 80, 70, 110, 130],
			    type: 'bar'
			}]
			 */

			//饼状图
			/*
			title: {
			    text: '某站点用户访问来源',
			    subtext: '纯属虚构',
			    left: 'center'
			},
			tooltip: {
			    trigger: 'item'
			},
			legend: {
			    orient: 'vertical',
			    left: 'left',
			},
			series: [
			    {
			        name: '访问来源',
			        type: 'pie',
			        radius: '50%',
			        data: [
			            {value: 1048, name: '搜索引擎'},
			            {value: 735, name: '直接访问'},
			            {value: 580, name: '邮件营销'},
			            {value: 484, name: '联盟广告'},
			            {value: 300, name: '视频广告'}
			        ],
			        emphasis: {
			            itemStyle: {
			                shadowBlur: 10,
			                shadowOffsetX: 0,
			                shadowColor: 'rgba(0, 0, 0, 0.5)'
			            }
			        }
			    }
			]
			 */

			//多个折线
			title : {
				text : '折线图堆叠'
			},
			tooltip : {
				trigger : 'axis'
			},
			legend : {
				data : [ '邮件营销', '联盟广告', '视频广告', '直接访问', '搜索引擎' ]
			},
			grid : {
				left : '3%',
				right : '4%',
				bottom : '3%',
				containLabel : true
			},
			toolbox : {
				feature : {
					saveAsImage : {}
				}
			},
			xAxis : {
				type : 'category',
				boundaryGap : false,
				data : [ '周一', '周二', '周三', '周四', '周五', '周六', '周日' ]
			},
			yAxis : {
				type : 'value'
			},
			series : [ {
				name : '邮件营销',
				type : 'line',
				stack : '总量',
				data : [ 120, 132, 101, 134, 90, 230, 210 ]
			}, {
				name : '联盟广告',
				type : 'line',
				stack : '总量',
				data : [ 220, 182, 191, 234, 290, 330, 310 ]
			}, {
				name : '视频广告',
				type : 'line',
				stack : '总量',
				data : [ 150, 232, 201, 154, 190, 330, 410 ]
			}, {
				name : '直接访问',
				type : 'line',
				stack : '总量',
				data : [ 320, 332, 301, 334, 390, 330, 320 ]
			}, {
				name : '搜索引擎',
				type : 'line',
				stack : '总量',
				data : [ 820, 932, 901, 934, 1290, 1330, 1320 ]
			} ]

		};

		// 使用刚指定的配置项和数据显示图表。
		myChart.setOption(option);
	</script>
</body>
</html>
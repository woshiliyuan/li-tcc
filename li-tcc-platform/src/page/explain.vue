<template>
    <div class="fillcontain">
        <head-top></head-top>
        <p class="explain_text">二阶段提交分布式事务后台管理系统</p>
        <p class="explain_text">Frontend by yuan.li</p>
        <p class="explain_text">Email:****@qq.com</p>
    </div>
</template>

<script>
	import headTop from '../components/headTop'
    export default {
    	components: {
    		headTop,
    	},
    }
</script>

<style lang="less">
	@import '../style/mixin';
	.explain_text{
		margin-top: 20px;
		text-align: center;
		font-size: 20px;
		color: #333;
	}
</style>

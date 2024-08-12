<!-- 友情链接模块 -->
<template>
<div class="tFriendsBox">
    <h1 class="friend-link-header">友链申请格式
        <h3>网站名称: 小王学Java的博客</h3>
        <h3>网站地址: https://www.OrangeLiker.com </h3>
        <h3>网站描述: 鞠鞠Bond童话里做英雄</h3>
        <h3>网站logo: https://www.OrangeLiker.com/Logo.png</h3>
</h1>
    <el-row>
        <el-col :span="12" class="tf-item" v-for="(item,index) in friendslink" :key="'f'+index">
            <a :href="item.address" target="_blank">
                <img :src="item.logo?item.logo:'static/img/tou.jpg'"  :onerror="$store.state.errorImg">
                <h4>{{item.name}}</h4>
                <p>{{item.description}}</p>
            </a>
        </el-col>
    </el-row>
</div>
</template>

<script>
import {getAllLink} from '../api/link.js'
export default {
    data() { //选项 / 数据
        return {
            friendslink:[]//友情链接
        }
    },
    methods: { //事件处理器
        getList(){
            getAllLink().then((response)=>{
                this.friendslink = response
            })
        }
    },
    components: { //定义组件

    },
    created() { //生命周期函数
        this.getList()
    }
}
</script>

<style>
/* .tFriendsBox{
    background: #fff;
    padding:15px;
    border-radius: 5px;
    margin-bottom: 40px;
}
.tFriendsBox h1{
    padding:15px 0;
    font-size: 25px;
    font-weight: bold;
} */
.tFriendsBox {
  background-image: url('/static/img/friendbg1.png'); /* 背景图片 */
  background-size: cover;
  background-position: center;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  transition: transform 0.3s ease; /* 添加悬浮动画 */
}

.tFriendsBox:hover {
  transform: scale(1.05); /* 悬浮时放大 */
}

.friend-link-header {
  font-family: 'Arial', sans-serif;
  color: white;
  text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5);
}

h3 {
  margin: 10px 0;
  color: #f0f0f0;
}
.tFriendsBox .tf-item{
    transition: all 0.3s ease-out;
    border-radius: 5px;
    position: relative;
    margin-top: 10px;
}
.tFriendsBox .tf-item:hover{
    background: rgba(230,244,250,.5);
}
.tFriendsBox .tf-item a{
    display: block;
    padding:0 10px 0 90px;
    height:90px;
}
.tFriendsBox .tf-item a img{
    width:60px;
    height:60px;
    border-radius: 50%;
    position: absolute;
    top:15px;
    left:15px;
    cursor: pointer;
    object-fit: cover;
}
.tFriendsBox .tf-item a h4{
    cursor: pointer;
    white-space: nowrap;
    text-overflow: ellipsis;
    overflow: hidden;
    font-size: 20px;
    padding-top:15px;
    font-weight: bold;
}
.tFriendsBox .tf-item a p{
    margin:10px 0;
    font-size: 12px;
    line-height: 24px;
    color:#f0fff8;
    cursor: pointer;
    white-space: nowrap;
    text-overflow: ellipsis;
}
</style>

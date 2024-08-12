<template>
  <div class="dashboard-container">
    <div class="dashboard-text">
      欢迎使用 OrangeLiker's Blog Management 🍊
    </div>
    <div>
      博主联系方式：<a href="https://space.bilibili.com/663528522">https://space.bilibili.com/663528522</a>
    </div>
    <div>
      项目资料获取：关注后会收到自动回复的交流群号，三连截图私聊群主即可获得项目资料。
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
  name: 'Dashboard',
  computed: {
    ...mapGetters([
      'name'
    ])
  }
}
</script>

<style lang="scss" scoped>
.dashboard {
  &-container {
    margin: 30px;
  }
  &-text {
    font-size: 40px;
    line-height: 50px;
    font-family: 'Roboto', sans-serif;
  }
}
</style>

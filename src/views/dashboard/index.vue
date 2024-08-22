<template>
  <div class="dashboard-container">
    <div class="dashboard-text">
      欢迎使用 OrangeLiker's Blog Management 🍊
    </div>
    <div class="action" style="margin-top: 20px; font-size: x-large;">
      博主联系方式：
      <div style="margin-top: 10px;">🐧：2814964382</div>
      <div style="margin-top: 10px;">Wechat：WAngZj_0908</div>
    </div>
    <div style="margin-top: 20px; font-size: x-large;">
      博客开源资料获取(点击即可)：<a href="https://github.com/OrangeLiker/OrangeL_Blog">https://github.com.OrangeLiker'sBlog🍊</a>
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

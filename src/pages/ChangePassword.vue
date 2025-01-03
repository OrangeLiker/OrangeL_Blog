<template>
  <div>
      <wbc-nav></wbc-nav>
      <div class="container">
          <div class="tcommonBox">
              <header>
                  <h1>修改密码</h1>
              </header>
              <section>
                  <el-form :model="form" ref="form" label-width="100px" class="change-password-form">
                      <!-- 原密码 -->
                      <el-form-item label="原密码" prop="oldPassword" :rules="[{ required: true, message: '请输入原密码', trigger: 'blur' }]">
                          <el-input v-model="form.oldPassword" type="password" placeholder="请输入原密码"></el-input>
                      </el-form-item>

                      <!-- 新密码 -->
                      <el-form-item label="新密码" prop="newPassword" :rules="[
                          { required: true, message: '请输入新密码', trigger: 'blur' },
                          { pattern: /^[a-zA-Z0-9_]{6,12}$/, message: '密码必须是6-12位的字母、数字或下划线', trigger: 'blur' }
                      ]">
                          <el-input v-model="form.newPassword" type="password" placeholder="请输入新密码"></el-input>
                      </el-form-item>

                      <!-- 确认密码 -->
                      <el-form-item label="确认密码" prop="confirmPassword" :rules="[
                          { required: true, message: '请确认新密码', trigger: 'blur' },
                          { validator: validateConfirmPassword, trigger: 'blur' }
                      ]">
                          <el-input v-model="form.confirmPassword" type="password" placeholder="请再次输入新密码"></el-input>
                      </el-form-item>

                      <!-- 提交按钮 -->
                      <div class="form-buttons">
                          <el-button @click="cancel" class="cancel-button" >取消修改</el-button>
                          <el-button type="primary" @click="submit" class="submit-button">立即重置</el-button>
                      </div>
                  </el-form>
              </section>
          </div>
      </div>
  </div>
</template>

<script>
import header from '../components/header.vue'
import { changePassword } from '../api/user.js'
import store from '../store'

export default {
    ame: 'ChangePassword',
    data() {
        return {
            form: {
                oldPassword: '',
                newPassword: '',
                confirmPassword: ''
            },
            userInfo: {},  // 本地存储的用户信息
            userId: null    // 用户ID，初始化为 null
        }
    },
    methods: {
        // 验证确认密码是否和新密码一致
        validateConfirmPassword(rule, value, callback) {
            if (value === '') {
                callback(new Error('请确认新密码'));
            } else if (value !== this.form.newPassword) {
                callback(new Error('新密码和确认密码不一致'));
            } else {
                callback();
            }
        },

        cancel() {
            this.$router.push('/UserInfo');
        },

        submit() {
            // 进行表单验证
            this.$refs.form.validate((valid) => {
                if (valid) {
                    // 从 localStorage 获取用户信息
                    if (localStorage.getItem('userInfo')) {
                        this.userInfo = JSON.parse(localStorage.getItem('userInfo'));
                        this.userId = this.userInfo.id;
                        console.log(this.userId); // 确认打印 userId
                    }

                    // 确认用户是否登录
                    if (!this.userInfo || !this.userId) {
                        this.$message.error('用户未登录');
                        return;
                    }

                    // 提交修改密码请求
                    const data = {
                        userId: this.userId,  // 传递用户ID
                        oldPassword: this.form.oldPassword,
                        newPassword: this.form.newPassword
                    };

                    // 调用 API 请求
                    changePassword(data.userId,data.oldPassword,data.newPassword).then((response) => {
                        this.$message.success('密码修改成功');
                        localStorage.removeItem('logUrl');
                        this.$router.push({path:'/Login?login=1'});
                    }).catch((error) => {
                        this.$message.error('密码修改失败');
                    });
                } else {
                    this.$message.error('请填写完整的表单');
                    return false;
                }
            });
        }
    },
    components: {
        'wbc-nav': header,
    }
}
</script>


<style scoped>
.change-password-form {
    max-width: 400px;
    margin: 0 auto;
}
.form-buttons {
    display: flex;
    justify-content: center;  /* 居中对齐 */
    gap: 20px;  /* 设置按钮间距 */
    margin-top: 20px;
}
.cancel-button {
    background-color: #f4f4f4;
    border: 1px solid #dcdfe6; /* 添加边框以确保按钮不易丢失 */
}
.submit-button {
    background-color: #409EFF;
}
</style>

<template>
	<view class="container">
		<view class="left-bottom-sign"></view>
		<view class="back-btn yticon icon-zuojiantou-up" @click="navBack"></view>
		<view class="right-top-sign"></view>
		<!-- 设置白色背景防止软键盘把下部绝对定位元素顶上来盖住输入框等 -->
		<view class="wrapper">
			<view class="left-top-sign">Register</view>
			<view class="welcome">
				欢迎来到校园跳蚤市场！
			</view>
			<view class="input-content">
				<view class="input-item">
					<text class="tit">用户名</text>
					<input v-model="newuser.username" placeholder="请输入用户名" />
				</view>
				<view class="input-item">
					<text class="tit">密码</text>
					<input v-model="newuser.password" placeholder="8-18位不含特殊字符的数字、字母组合" placeholder-class="input-empty"
						maxlength="20" password />
				</view>
				<view class="input-item">
					<text class="tit">学院</text>
					<input v-model="newuser.faculty" placeholder="请输入所在学院" />
				</view>
				<view class="input-item">
					<text class="tit">地址</text>
					<input v-model="newuser.address" placeholder="请输入您的地址" />
				</view>
				<view class="input-item">
					<text class="tit">QQ号</text>
					<input v-model="newuser.qqnumber" placeholder="请输入您的QQ号" />
				</view>
				<view class="input-item">
					<text class="tit">微信号</text>
					<input v-model="newuser.vxnumber" placeholder="请输入您的微信号" />
				</view>
			</view>
			<button class="confirm-btn" @click="toRegister">注册</button>
		</view>
	</view>

</template>

<script>
	export default {
		data() {
			return {
				newuser: {
					id: -1,
					username: '',
					password: '',
					faculty: '',
					address: '',
					qqnumber: '',
					vxnumber: ''
				}

			}
		},
		methods: {
			navBack() {
				uni.navigateBack()
			},
			toRegister() {
				var that = this
				uni.request({
					url: 'http://localhost:8080/user/adduser',
					method: 'POST',
					data: {
						id: -1,
						username: that.newuser.username,
						password: that.newuser.password,
						faculty: that.newuser.faculty,
						address: that.newuser.address,
						qqnumber: that.newuser.qqnumber,
						vxnumber: that.newuser.vxnumber
					},
					header: {
						'content-type': 'application/x-www-form-urlencoded'
					},
					success: function(res) {
						console.log(res)
						uni.showToast({
							title: '注册成功！',
							icon: 'success'
						})
						setTimeout(function() {
							uni.navigateTo({
								url: './login'
							})
						}, 2000);
					},
					fail: function(e) {
						console.log(e)
					}

				});
			}

		}
	}
</script>

<style lang='scss'>
	page {
		background: #fff;
	}

	.container {
		padding-top: 115px;
		position: relative;
		width: 100vw;
		height: 140vh;
		overflow: hidden;
		background: #fff;
	}

	.wrapper {
		position: relative;
		z-index: 90;
		background: #fff;
		padding-bottom: 40upx;
	}

	.back-btn {
		position: absolute;
		left: 40upx;
		z-index: 9999;
		padding-top: var(--status-bar-height);
		top: 40upx;
		font-size: 40upx;
		color: $font-color-dark;
	}

	.left-top-sign {
		font-size: 120upx;
		color: $page-color-base;
		position: relative;
		left: -16upx;
	}

	.right-top-sign {
		position: absolute;
		top: 80upx;
		right: -30upx;
		z-index: 95;

		&:before,
		&:after {
			display: block;
			content: "";
			width: 400upx;
			height: 80upx;
			background: #b4f3e2;
		}

		&:before {
			transform: rotate(50deg);
			border-radius: 0 50px 0 0;
		}

		&:after {
			position: absolute;
			right: -198upx;
			top: 0;
			transform: rotate(-50deg);
			border-radius: 50px 0 0 0;
			/* background: pink; */
		}
	}

	.left-bottom-sign {
		position: absolute;
		left: -270upx;
		bottom: -320upx;
		border: 100upx solid #d0d1fd;
		border-radius: 50%;
		padding: 180upx;
	}

	.welcome {
		position: relative;
		left: 50upx;
		top: -90upx;
		font-size: 46upx;
		color: #555;
		text-shadow: 1px 0px 1px rgba(0, 0, 0, .3);
	}

	.input-content {
		padding: 0 60upx;
	}

	.input-item {
		display: flex;
		flex-direction: column;
		align-items: flex-start;
		justify-content: center;
		padding: 0 30upx;
		background: $page-color-light;
		height: 120upx;
		border-radius: 4px;
		margin-bottom: 50upx;

		&:last-child {
			margin-bottom: 0;
		}

		.tit {
			height: 50upx;
			line-height: 56upx;
			font-size: $font-sm+2upx;
			color: $font-color-base;
		}

		input {
			height: 60upx;
			font-size: $font-base + 2upx;
			color: $font-color-dark;
			width: 100%;
		}
	}

	.confirm-btn {
		width: 630upx;
		height: 76upx;
		line-height: 76upx;
		border-radius: 50px;
		margin-top: 70upx;
		background: $uni-color-primary;
		color: #fff;
		font-size: $font-lg;

		&:after {
			border-radius: 100px;
		}
	}

	.forget-section {
		font-size: $font-sm+2upx;
		color: $font-color-spec;
		text-align: center;
		margin-top: 40upx;
	}

	.register-section {
		position: absolute;
		left: 0;
		bottom: 50upx;
		width: 100%;
		font-size: $font-sm+2upx;
		color: $font-color-base;
		text-align: center;

		text {
			color: $font-color-spec;
			margin-left: 10upx;
		}
	}
</style>

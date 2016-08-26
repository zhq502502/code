<div id="d_tab{dede:field name='id'/}" class="itemcontent swiper-container sw{dede:field name='id'/}" style="display: none;padding: 20px 0 20px 0;height: 220px">
	      	<div class="swiper-wrapper" style="">
	      	{dede:arclist typt='son' limit='0,20'  flag='h'}
			<div class="item2 swiper-slide">
				<div class="item1_img">
					<a href='[field:arcurl/]'><img alt="" src='[field:picname/] '/></a>
				</div>
				<div class="item1_content">
					<div class="item1_content_title"><a href='[field:arcurl/]'>[field:title/]</a></div>
					<div class="item1_content_text">></div>
				</div>
			</div>
			{/dede:arclist}
			</div>
			<!-- 如果需要导航按钮 -->
		    <div class="swiper-button-prev"></div>
		    <div class="swiper-button-next"></div>
			</div>
			<script>
			var mySwiper{dede:field name='id'/} = new Swiper (".sw{dede:field name='id'/}", {
				slidesPerView : 5,
				centeredSlides : false,
				autoplay: 2000,
			  direction: 'horizontal',
			  loop: true,
			  // 如果需要前进后退按钮
			  nextButton: '.swiper-button-next',
			  prevButton: '.swiper-button-prev',
			  
			  // 如果需要滚动条
			  /*scrollbar: '.swiper-scrollbar',*/
			}) ;
			</script>
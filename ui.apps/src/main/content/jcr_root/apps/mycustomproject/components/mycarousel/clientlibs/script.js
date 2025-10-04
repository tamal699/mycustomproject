document.addEventListener("DOMContentLoaded", function () {
  const slides = document.querySelector(".carousel-slides");
  const slideCount = slides ? slides.children.length : 0;
  let currentIndex = 0;
  let autoPlayInterval;

  function showSlide(index) {
    if (!slides) return;
    slides.style.transform = `translateX(-${index * 100}%)`;
  }

  function nextSlide() {
    currentIndex = (currentIndex + 1) % slideCount;
    showSlide(currentIndex);
  }

  function prevSlide() {
    currentIndex = (currentIndex - 1 + slideCount) % slideCount;
    showSlide(currentIndex);
  }

  function startAutoPlay() {
    stopAutoPlay(); // prevent multiple intervals
    autoPlayInterval = setInterval(nextSlide, 5000);
  }

  function stopAutoPlay() {
    if (autoPlayInterval) clearInterval(autoPlayInterval);
  }

  // Attach button listeners (only if buttons exist)
  const nextBtn = document.querySelector(".next");
  const prevBtn = document.querySelector(".prev");

  if (nextBtn) {
    nextBtn.addEventListener("click", () => {
      nextSlide();
      startAutoPlay(); // restart autoplay after manual click
    });
  }

  if (prevBtn) {
    prevBtn.addEventListener("click", () => {
      prevSlide();
      startAutoPlay();
    });
  }

  // Initialize
  showSlide(currentIndex);
  startAutoPlay();
});

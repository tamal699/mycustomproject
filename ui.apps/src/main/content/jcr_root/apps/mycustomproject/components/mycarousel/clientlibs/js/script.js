document.addEventListener("DOMContentLoaded", function () {
  const slides = document.querySelector(".carousel-slides");
  const dotsContainer = document.querySelector(".carousel-dots");
  const slideCount = slides ? slides.children.length : 0;
  let currentIndex = 0;
  let autoPlayInterval;

  // === Create Dots Dynamically ===
  if (dotsContainer && slideCount > 0) {
    for (let i = 0; i < slideCount; i++) {
      const dot = document.createElement("span");
      dot.classList.add("dot");
      if (i === 0) dot.classList.add("active");
      dot.addEventListener("click", () => {
        currentIndex = i;
        showSlide(currentIndex);
        startAutoPlay(); // restart autoplay after click
      });
      dotsContainer.appendChild(dot);
    }
  }

  const dots = dotsContainer ? dotsContainer.querySelectorAll(".dot") : [];

  function updateDots() {
    if (!dots.length) return;
    dots.forEach((dot, idx) => {
      dot.classList.toggle("active", idx === currentIndex);
    });
  }

  function showSlide(index) {
    if (!slides) return;
    slides.style.transform = `translateX(-${index * 100}%)`;
    updateDots();
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
    stopAutoPlay(); // avoid stacking multiple intervals
    autoPlayInterval = setInterval(nextSlide, 5000);
  }

  function stopAutoPlay() {
    if (autoPlayInterval) clearInterval(autoPlayInterval);
  }

  // === Buttons ===
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

  // === Initialize ===
  showSlide(currentIndex);
  startAutoPlay();
});

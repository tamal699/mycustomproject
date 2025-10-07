/**
 * toggle.js
 * Handles showing and hiding sections in Page Info Dashboard
 */
document.addEventListener("DOMContentLoaded", function () {
  // Select all dashboard instances (in case multiple are added to a page)
  document.querySelectorAll(".page-info-dashboard").forEach(function (dashboard) {
    const pageInfoSection = dashboard.querySelector("#pageInfoSection");
    const resourceInfoSection = dashboard.querySelector("#resourceInfoSection");
    const showPageInfoBtn = dashboard.querySelector("#showPageInfoBtn");
    const showResourceInfoBtn = dashboard.querySelector("#showResourceInfoBtn");

    if (!showPageInfoBtn || !showResourceInfoBtn) return;

    // Handle Show Page Info button
    showPageInfoBtn.addEventListener("click", function () {
      pageInfoSection.classList.add("active");
      resourceInfoSection.classList.remove("active");
    });

    // Handle Show Resource Info button
    showResourceInfoBtn.addEventListener("click", function () {
      resourceInfoSection.classList.add("active");
      pageInfoSection.classList.remove("active");
    });
  });
});

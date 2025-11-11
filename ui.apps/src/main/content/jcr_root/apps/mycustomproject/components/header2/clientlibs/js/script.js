(function (document) {
    "use strict";

    document.addEventListener("DOMContentLoaded", function () {
        const input = document.getElementById("header-search-input");
        const button = document.getElementById("header-search-btn");
        const resultsBox = document.getElementById("header-search-results");

        if (!input || !button || !resultsBox) return;

        let debounceTimer;

        function performSearch(query) {
            if (!query) {
                resultsBox.innerHTML = "";
                resultsBox.style.display = "none";
                return;
            }

            fetch(`/bin/mycustomproject/search?q=${encodeURIComponent(query)}`)
                .then(res => res.json())
                .then(data => {
                    resultsBox.innerHTML = "";

                    if (data.length === 0) {
                        resultsBox.innerHTML = "<div class='header-search-no-results'>No results found</div>";
                        resultsBox.style.display = "block";
                        return;
                    }

                    data.forEach(item => {
                        const link = document.createElement("a");
                        link.href = item.path + ".html";
                        link.textContent = item.title;
                        link.className = "header-search-item";
                        resultsBox.appendChild(link);
                    });

                    resultsBox.style.display = "block";
                })
                .catch(err => console.error("Search failed:", err));
        }


        input.addEventListener("input", function () {
            clearTimeout(debounceTimer);
            const query = input.value.trim();
            debounceTimer = setTimeout(() => performSearch(query), 300);
        });


        button.addEventListener("click", () => performSearch(input.value.trim()));


        document.addEventListener("click", (e) => {
            if (!resultsBox.contains(e.target) && !input.contains(e.target)) {
                resultsBox.innerHTML = "";
                resultsBox.style.display = "none";
            }
        });
    });
})(document);

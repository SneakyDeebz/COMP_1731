document.addEventListener("DOMContentLoaded", function() {
    const url = "https://www.randyconnolly.com/funwebdev/3rd/api/colors/sample-colors.php";
    const loader = document.getElementById("loader");
    const schemeGroup = document.querySelector(".scheme-group");
    const aside = document.querySelector("aside");
    const fieldset = aside.querySelector("fieldset");
    let colorSchemes = [];
    
    loader.style.display = "none"; 
    loader.style.display = "inline-block"; 
    
    console.log("Fetching data from:", url);
    
    fetch(url)
        .then(response => response.json())
        .then(data => {
            console.log("Full API Response:", data); 
            
            if (Array.isArray(data)) {
                colorSchemes = data;
            } else {
                console.error("Unexpected API response format:", data);
                return;
            }
 
            console.log("Processed Color Schemes:", colorSchemes);
            displayColorSchemes(colorSchemes);
 
            loader.style.display = "none"; 
        })
        .catch(error => {
            console.error("Error fetching data:", error);
            loader.style.display = "none";
        });
 
    function displayColorSchemes(schemes) {
        schemeGroup.innerHTML = ""; 
        
        schemes.forEach(scheme => {
            console.log("Processing scheme:", scheme); 
            
            const article = document.createElement("article");
            const heading = document.createElement("h3");
            heading.textContent = scheme.title || "Unnamed Scheme";
            article.appendChild(heading);
 
            const section = document.createElement("section");
            section.className = "scheme";
 
            const preview = document.createElement("div");
            preview.className = "preview";
 
            
            if (!Array.isArray(scheme.scheme)) {
                console.error("Missing scheme.scheme in:", scheme);
                return;
            }
 
            scheme.scheme.forEach(item => {
                console.log("Processing color item:", item); 
 
                
                if (item && item.web) {
                    console.log("Valid color found:", item.web);
 
                    const colorBox = document.createElement("div");
                    colorBox.className = "color-box";
                    colorBox.style.backgroundColor = item.web;
                    preview.appendChild(colorBox);
                } else {
                    
                    console.warn("Skipping invalid color. Item details:", item);
                }
            });
 
            section.appendChild(preview);
 
            const actions = document.createElement("div");
            actions.className = "actions";
 
            const button = document.createElement("button");
            button.textContent = "View";
            button.dataset.id = scheme.id; 
            actions.appendChild(button);
 
            section.appendChild(actions);
            article.appendChild(section);
            schemeGroup.appendChild(article);
        });
    }
 
    
    schemeGroup.addEventListener("click", function(event) {
        if (event.target.tagName === "BUTTON" && event.target.textContent === "View") {
            const schemeId = parseInt(event.target.dataset.id, 10); 
            const scheme = colorSchemes.find(s => s.id === schemeId);
            if (scheme) {
                displaySchemeDetails(scheme);
            }
        }
    });
 
    function displaySchemeDetails(scheme) {
        fieldset.innerHTML = ""; 
        aside.querySelector("h2").textContent = scheme.title || "Unnamed Scheme";
 
        scheme.scheme.forEach(item => {
            
            const colorRow = document.createElement("div");
            colorRow.className = "colorRow";
            
            
            const detailBox = document.createElement("div");
            detailBox.className = "detailBox";
            detailBox.style.backgroundColor = item.web;
            
            
            const hexSpan = document.createElement("span");
            hexSpan.textContent = item.web;
            
            
            const rgbSpan = document.createElement("span");
            rgbSpan.textContent = `rgb(${item.color.red},${item.color.green},${item.color.blue})`;
            
            
            const nameLabel = document.createElement("label");
            nameLabel.textContent = item.name;
            
            
            colorRow.appendChild(detailBox);
            colorRow.appendChild(hexSpan);
            colorRow.appendChild(rgbSpan);
            colorRow.appendChild(nameLabel);
            
            
            fieldset.appendChild(colorRow);
        });
    }
 });
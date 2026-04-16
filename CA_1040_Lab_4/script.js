


var currentPage = window.location.pathname;
var secondsOnPage = 0;   

// ============================================
//  CUSTOM FUNCTION 1: Generate a time-based greeting
// ============================================
function getGreeting() {
  var hour = new Date().getHours();  
  var greeting;
  if (hour < 12) {
    greeting = "Good Morning";
  } else if (hour < 18) {
    greeting = "Good Afternoon";
  } else {
    greeting = "Good Evening";
  }
  return greeting;
}

// ============================================
//  CUSTOM FUNCTION 2: Format a date nicely
// ============================================
function formatDate(dateObj) {
  
  var options = { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' };
  var dateString = dateObj.toLocaleDateString('en-US', options);
  return dateString.toUpperCase();  
}

// ============================================
//  ARRAY — Skills data source
// ============================================
var skills = [
  "HTML5", "CSS3", "JavaScript", "Python",
  "Git & GitHub", "Responsive Design", "Figma", "SQL"
];

// ============================================
//  MAP — Categories and their descriptions
// ============================================
var categoryMap = new Map([
  ["Design",      "I love clean UI, typography, and color theory."],
  ["Development", "Building things with code is my superpower."],
  ["Learning",    "Always reading docs, tutorials, and new frameworks."],
  ["Music",       "Lo-fi beats help me focus while I code."]
]);

// ============================================
//  OBJECT — Fun facts data source
// ============================================
var funFacts = {
  facts: [
    "I have visited 8 different states in the US.",
    "I learned to type at 55 WPM in middle school.",
    "My first programming language was Scratch.",
    "I once built a website in under 3 hours for a hackathon.",
    "Coffee is my secret productivity tool."
  ],
  
  getRandom: function() {
    var index = Math.floor(Math.random() * this.facts.length);  // Math.random + Math.floor
    return this.facts[index];
  }
};

// ============================================
//  TRY–CATCH: Safely parse localStorage value
// ============================================
function getSavedTheme() {
  var saved;
  try {
    saved = localStorage.getItem("portfolioTheme");
    if (saved === null) {
      saved = "default";  
    }
  } catch (error) {
    
    console.log("Could not read localStorage:", error.message);
    saved = "default";
  }
  return saved;
}

// ============================================
//  Apply a theme class to body
// ============================================
function applyTheme(themeName) {
  document.body.classList.remove("theme-dark", "theme-warm");
  if (themeName === "dark") {
    document.body.classList.add("theme-dark");
  } else if (themeName === "warm") {
    document.body.classList.add("theme-warm");
  }
  
}


var savedTheme = getSavedTheme();
applyTheme(savedTheme);

// ============================================
//  PAGE-SPECIFIC LOGIC
// ============================================
window.addEventListener("load", function() {

  // ---- INDEX.HTML ----
  if (document.getElementById("greeting-text")) {
    setupIndexPage();
  }

  // ---- ABOUT.HTML ----
  if (document.getElementById("toggle-btn")) {
    setupAboutPage();
  }

  // ---- CONTACT.HTML ----
  if (document.getElementById("contact-form")) {
    setupContactPage();
  }

});

// ============================================
//  INDEX PAGE SETUP
// ============================================
function setupIndexPage() {

  
  var greetingEl = document.getElementById("greeting-text");
  greetingEl.textContent = getGreeting() + "! 👋";

  
  var dateEl = document.getElementById("today-date");
  var today = new Date();
  dateEl.textContent = "Today is " + formatDate(today);

  
  var skillsList = document.getElementById("skills-list");
  skills.forEach(function(skill) {
    var li = document.createElement("li");
    li.textContent = skill;
    skillsList.appendChild(li);
  });

  
  var categoryDisplay = document.getElementById("category-display");
  var keys = Array.from(categoryMap.keys());
  var randomKey = keys[Math.floor(Math.random() * keys.length)];
  var description = categoryMap.get(randomKey);

  categoryDisplay.innerHTML =
    "<h3>" + randomKey + "</h3>" +
    "<p>" + description + "</p>";

  
  var changeBtn = document.getElementById("change-btn");
  var taglineEl = document.getElementById("tagline");

  var quotes = [
    "Turning coffee into code since 2022. ☕",
    "Pixel-perfect designs are my passion. 🎨",
    "Learning something new every single day. 📚",
    "Building the web, one tag at a time. 🌐"
  ];
  var quoteIndex = 0;

  changeBtn.addEventListener("click", function() {
    quoteIndex = (quoteIndex + 1) % quotes.length;
    taglineEl.textContent = quotes[quoteIndex];

    
    var hasEmoji = quotes[quoteIndex].indexOf("☕") !== -1 ||
                   quotes[quoteIndex].indexOf("🎨") !== -1;
    
    taglineEl.style.color = hasEmoji ? "var(--accent)" : "var(--text-light)";
  });

  
  var timerCountEl = document.getElementById("timer-count");
  setInterval(function() {
    secondsOnPage++;
    timerCountEl.textContent = secondsOnPage;
  }, 1000);

  
  try {
    localStorage.setItem("lastPage", "index.html");
  } catch (e) {
    console.log("localStorage write failed:", e.message);
  }
}

// ============================================
//  ABOUT PAGE SETUP
// ============================================
function setupAboutPage() {

  
  var toggleBtn = document.getElementById("toggle-btn");
  var moreInfo = document.getElementById("more-info");

  
  var savedExpanded;
  try {
    savedExpanded = localStorage.getItem("moreInfoExpanded");
  } catch (e) {
    savedExpanded = null;
  }

  if (savedExpanded === "true") {
    moreInfo.classList.remove("collapsed");
    moreInfo.classList.add("expanded");
    toggleBtn.textContent = "− Hide Details";
  }

  toggleBtn.addEventListener("click", function() {
    
    moreInfo.classList.toggle("collapsed");
    moreInfo.classList.toggle("expanded");

    var isExpanded = moreInfo.classList.contains("expanded");
    toggleBtn.textContent = isExpanded ? "− Hide Details" : "+ Show More About Me";

    
    try {
      localStorage.setItem("moreInfoExpanded", isExpanded.toString());
    } catch (e) {
      console.log("Could not save preference:", e.message);
    }
  });

  
  var factBtn = document.getElementById("fact-btn");
  var funFactEl = document.getElementById("fun-fact");

  factBtn.addEventListener("click", function() {
    funFactEl.textContent = funFacts.getRandom();
  });

  
  var luckyBtn = document.getElementById("lucky-btn");
  var luckyEl = document.getElementById("lucky-number");

  luckyBtn.addEventListener("click", function() {
    var lucky = Math.floor(Math.random() * 100) + 1;  
    luckyEl.textContent = lucky;
  });

 
  var themeButtons = document.querySelectorAll(".theme-btn");  
  var savedMsg = document.getElementById("theme-saved-msg");

  themeButtons.forEach(function(btn) {
    btn.addEventListener("click", function() {
      var chosen = btn.getAttribute("data-theme");
      applyTheme(chosen);

      
      try {
        localStorage.setItem("portfolioTheme", chosen);
       
        savedMsg.textContent = `✅ Theme "${chosen}" saved! It will load next time you visit.`;
      } catch (e) {
        savedMsg.textContent = "Could not save preference.";
      }
    });
  });

  
  setTimeout(function() {
    var local_message = "Welcome back! Check out the theme switcher below.";  
    console.log("Timed message:", local_message);
  }, 2000);

  
  try {
    localStorage.setItem("lastPage", "about.html");
  } catch (e) { /* silent */ }
}

// ============================================
//  CONTACT PAGE SETUP
// ============================================
function setupContactPage() {

  var form = document.getElementById("contact-form");
  var nameInput = document.getElementById("name");
  var emailInput = document.getElementById("email");
  var messageInput = document.getElementById("message");
  var successMsg = document.getElementById("success-msg");

  var nameError = document.getElementById("name-error");
  var emailError = document.getElementById("email-error");
  var messageError = document.getElementById("message-error");
  var charCount = document.getElementById("char-count");

  
  messageInput.addEventListener("input", function() {
    var length = messageInput.value.length;
    charCount.textContent = `${length} character${length !== 1 ? "s" : ""}`;
  });

  
  form.addEventListener("submit", function(event) {
    event.preventDefault();  

    
    nameError.textContent = "";
    emailError.textContent = "";
    messageError.textContent = "";
    successMsg.classList.add("hidden");

    var isValid = true;

    
    var nameVal = nameInput.value.trim();  
    if (nameVal === "") {
      nameError.textContent = "Name must not be empty.";
      isValid = false;
    }

    
    var emailVal = emailInput.value.trim();
    if (emailVal === "") {
      emailError.textContent = "Email must not be empty.";
      isValid = false;
    } else if (emailVal.indexOf("@") === -1) {
      emailError.textContent = 'Email must contain "@".';
      isValid = false;
    }

    
    var msgVal = messageInput.value.trim();
    if (msgVal === "") {
      messageError.textContent = "Message must not be empty.";
      isValid = false;
    } else if (msgVal.length < 10) {
      messageError.textContent = "Message is too short (at least 10 characters).";
      isValid = false;
    }

    
    if (isValid) {
      successMsg.classList.remove("hidden");
      form.reset();
      charCount.textContent = "0 characters";

      
      setTimeout(function() {
        successMsg.classList.add("hidden");
      }, 5000);
    }
  });

  
  try {
    localStorage.setItem("lastPage", "contact.html");
  } catch (e) { /* silent */ }
}
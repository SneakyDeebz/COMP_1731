const symbolPlay = '⯈';
const symbolPause = '❚ ❚';
const files = ['Nature-8399','River-655','Waterfall-941','Wave-2737'];

document.addEventListener('DOMContentLoaded', function() {
  const video = document.querySelector('#vidPlayer');
  const playButton = document.querySelector('#play');
  const stopButton = document.querySelector('#stop');
  const progress = document.querySelector('#progress');
  const progressFilled = document.querySelector('#progressFilled');
  const skipButtons = document.querySelectorAll('[data-skip]');
  const volumeSlider = document.querySelector('#volume');
  const aside = document.querySelector('aside');
  
  
  function createVideoGallery() {
    
    aside.style.display = 'flex';
    aside.style.justifyContent = 'center';
    aside.style.flexWrap = 'wrap';
    
    files.forEach(file => {
     
      const img = document.createElement('img');
      img.src = `images/${file}.jpg`;
      img.alt = file.replace('-', ' '); 
      img.height = 80; 
      img.style.cursor = 'pointer';
      img.style.margin = '0 2px'; 
      img.style.border = '1px solid #eae2b7';
      
      
      img.addEventListener('click', function() {
        video.pause();
        video.currentTime = 0;
        
        
        video.src = `videos/${file}.mp4`;
        playButton.textContent = symbolPlay;
        progressFilled.style.flexBasis = '0%';
        
        
        video.load();
        video.play();
        playButton.textContent = symbolPause;
      });
      
      
      aside.appendChild(img);
    });
  }
  
  
  function togglePlay() {
    if (video.paused) {
      video.play();
      playButton.textContent = symbolPause;
    } else {
      video.pause();
      playButton.textContent = symbolPlay;
    }
  }
  
  
  function stopVideo() {
    video.pause();
    video.currentTime = 0;
    playButton.textContent = symbolPlay;
    progressFilled.style.flexBasis = '0%';
  }
  
  
  function skip() {
    const skipValue = parseFloat(this.dataset.skip);
    video.currentTime += skipValue;
  }
  
 
  function updateProgress() {
    if (!isNaN(video.duration)) {
      const percent = (video.currentTime / video.duration) * 100;
      progressFilled.style.flexBasis = `${percent}%`;
    }
  }
  
  
  function scrub(e) {
    const scrubTime = (e.offsetX / progress.offsetWidth) * video.duration;
    video.currentTime = scrubTime;
  }
  
  
  function handleVolumeChange() {
    video.volume = this.value;
  }
  
  
  function loadInitialVideo() {
    video.src = `videos/${files[0]}.mp4`;
    video.load();
  }
  
  
  video.addEventListener('click', togglePlay);
  playButton.addEventListener('click', togglePlay);
  stopButton.addEventListener('click', stopVideo);
  skipButtons.forEach(button => button.addEventListener('click', skip));
  video.addEventListener('timeupdate', updateProgress);
  volumeSlider.addEventListener('change', handleVolumeChange);
  volumeSlider.addEventListener('mousemove', handleVolumeChange);
  
 
  let mousedown = false;
  progress.addEventListener('click', scrub);
  progress.addEventListener('mousemove', (e) => mousedown && scrub(e));
  progress.addEventListener('mousedown', () => mousedown = true);
  progress.addEventListener('mouseup', () => mousedown = false);
  
 
  loadInitialVideo();
  createVideoGallery();
});
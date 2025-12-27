<script>
  import '../app.css';
</script>

<main>
  <div class="logo-container">
    <img src="spring-boot.svg" alt="SpringBoot Logo" class="logo spring-boot" />
    <span class="plus">+</span>
    <img src="svelte.svg" alt="Svelte Logo" class="logo svelte" />
  </div>

  <h1>Spring Boot + Svelte</h1>

  <p>
    This is a statically-served SvelteKit landing page inside a Spring Boot
    application.
  </p>
  <p class="read-the-docs">
    The Svelte app is built as a set of static files which are then served by
    Spring Boot.
  </p>
</main>

<style>
  :root {
    font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen,
      Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
    --primary-color: #ff3e00;
    --secondary-color: #6db33f;
    --text-color: #333;
    --background-color: #f4f4f4;
  }

  main {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    text-align: center;
    height: 100vh;
    background-color: var(--background-color);
    color: var(--text-color);
  }

  .logo-container {
    display: flex;
    align-items: center;
    justify-content: center;
    margin-bottom: 2rem;
  }

  .logo {
    height: 6em;
    padding: 1.5em;
    will-change: filter;
    transition: filter 300ms;
  }
  .logo.svelte:hover {
    filter: drop-shadow(0 0 2em var(--primary-color));
  }
  .logo.spring-boot:hover {
    filter: drop-shadow(0 0 2em var(--secondary-color));
  }
  .plus {
    font-size: 3em;
    color: #ccc;
    margin: 0 1rem;
  }

  h1 {
    color: var(--primary-color);
    text-transform: uppercase;
    font-size: 4rem;
    font-weight: 100;
    line-height: 1.1;
    margin: 0 auto 1rem;
  }

  p {
    max-width: 520px;
    margin: 1rem auto;
    line-height: 1.5;
  }

  .read-the-docs {
    color: #888;
  }
</style>

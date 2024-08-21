
# Simple Domain-Limited Web Crawler

## Overview

This repository contains a simple web crawler implemented in Java. The crawler is designed to explore and list all the pages within a given domain, ensuring that external links are not followed. It handles the task of navigating through web pages, identifying links, and maintaining a record of visited URLs. This project was developed as part of a technical assessment for a Full-stack Developer role at Sedna.

To ground my knowledge and to understand best practice and modern use cases on this topic, I will be reading the following foundational papers and online resources:

- Brin, S. and Page, L., 1998. The Anatomy of a Large-Scale Hypertextual Web Search Engine. Computer Networks and ISDN Systems, 30(1-7), pp.107-117. Available at: https://snap.stanford.edu/class/cs224w-readings/Brin98Anatomy.pdf
- Chakrabarti, S., Van den Berg, M. and Dom, B., 1999. Focused Crawling: A New Approach to Topic-Specific Web Resource Discovery. Computer Networks, 31(11-16), pp.1623-1640. Available at: https://www.sciencedirect.com/science/article/abs/pii/S1389128699000523
- Cloudflare. (n.d.) What is a web crawler? Available at: https://www.cloudflare.com/en-gb/learning/bots/what-is-a-web-crawler/ (Accessed: 16 August 2024).
- Bowen, G. (2023) What is website crawling & how does it work? Search Engine Journal. Available at: https://www.searchenginejournal.com/website-crawling/485275/#:~:text=A%20web%20crawler%20works%20by,links%20to%20other%20web%20pages (Accessed: 16 August 2024).

A strong foundation in web crawlers is key to understanding Sedna's approach to email and data search. Web crawlers are central to collecting and indexing communication data from multiple sources, enabling efficient search, retrieval, and automation within their products. 

## Features

- **Domain-Limited Crawling:** The crawler restricts its exploration to the specified domain, preventing any external links from being followed.
- **Link Tracking:** Utilizes a `Set` to ensure each page within the domain is only visited once, optimizing the crawling process.
- **Flexible and Extensible:** Built with simplicity in mind, this crawler can be easily extended to support additional features or configurations as needed.

## Requirements

- **Java 8 or higher**
- **Jsoup Library**: The project relies on the Jsoup library for parsing and navigating HTML documents. Ensure that Jsoup is included in your project dependencies.

### Maven Dependency

If you're using Maven, add the following dependency to your `pom.xml`:

```xml
<dependency>
    <groupId>org.jsoup</groupId>
    <artifactId>jsoup</artifactId>
    <version>1.16.1</version>
</dependency>
```

Alternatively, you can download Jsoup directly from [here](https://jsoup.org/download) and include it in your project manually.

## How It Works

The web crawler begins at a specified start URL and recursively explores all accessible pages within the same domain. It identifies hyperlinks on each page and continues the crawl process until all pages within the domain have been visited.

### Key Components

- **CreepyCrawler.java**: The main class responsible for executing the crawl process. It contains methods for fetching web pages, extracting links, and tracking visited URLs.
- **Jsoup Library**: Handles the heavy lifting of HTTP requests and HTML parsing.

### Usage

To run the web crawler:

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/katlego95/creepy-crawler.git
   ```
2. **Navigate to the Project Directory:**
   ```bash
   cd web-crawler
   ```
3. **Compile and Run the Program:**
   ```bash
   javac CreepyCrawler.java RobotsTextHandler.java UrlRules.java
   java CreepyCrawler
   ```
4. The crawler will output all pages found within the specified domain.

### Example

Given a starting URL like `https://www.sedna.com`, the crawler will output a list of all internal pages on the Sedna website:

```plaintext
Crawled pages:
https://www.sedna.com/
https://www.sedna.com/about
https://www.sedna.com/contact
...
```

## Customization

This web crawler is designed with simplicity and clarity in mind. However, it can be easily customized to:
- Handle different content types
- Implement multi-threading for faster crawling
- Apply custom filters or processing rules on the discovered links
- Extensive J Unit testing using sites like : https://crawler-test.com/

## Contributing

Contributions to this project are welcome. Feel free to fork the repository, make changes, and submit a pull request. If you encounter any issues or have suggestions for improvements, please open an issue in the repository.

## License

This project is open-source and available under the MIT License.

## Contact

For any questions or further information, please contact me at [katlegobeta@gmail.com](mailto:katlegobeat@gmail.com).
